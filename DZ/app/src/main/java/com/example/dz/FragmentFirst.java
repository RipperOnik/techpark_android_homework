package com.example.dz;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentFirst extends Fragment {
    private static final String EXTRA = "ARRAY";
    private final static int COMMON_SIZE = 100;
    private int SavedSize;
    private MyDataAdapter mAdapter;
    private Button addButton;
    private RecyclerView recyclerView;
    private FragmentNavigator navigator;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            SavedSize = savedInstanceState.getInt(EXTRA);
        }
        else
            SavedSize= COMMON_SIZE;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        navigator = MainActivity.getNavigator(); // new Navigator;
        if(mAdapter == null)
            mAdapter = new MyDataAdapter();
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        recyclerView = view.findViewById(R.id.list);
        int columns = getResources().getBoolean(R.bool.is_horizontal) ? 4 : 3;
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), columns));
        recyclerView.setAdapter(mAdapter);
        addButton = view.findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> mAdapter.addElement());
        mAdapter.setData(SavedSize);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addButton = null;
        recyclerView = null;
        mAdapter = null;
        navigator = null;
    }

    public void onSaveInstanceState(@NonNull  Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(EXTRA, SavedSize);
    }

    class MyDataAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private final List<Integer> mData;
        MyDataAdapter() {
            mData = new ArrayList<>();
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            int data = mData.get(position), color;
            holder.textView.setText(data+"");
            color = (data%2 == 0) ?
                    Color.RED : Color.BLUE;
            holder.textView.setTextColor(color);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        void setData(int size) {
            for(int i = getItemCount() + 1; i <= size; i++)
                mData.add(i);
            SavedSize = size;
            notifyDataSetChanged();
        }
        void addElement() {
            int pos = getItemCount() + 1;
            mData.add(pos);
            SavedSize++;
            notifyItemInserted(pos);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.number);
            textView.setOnClickListener( v -> {
                int pos = getAdapterPosition() + 1;
                int currentTextColor = textView.getCurrentTextColor();
                navigator.navigateToAnotherFragment(pos, currentTextColor);
            });
        }
    }
}
