package com.example.dz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentSecond extends Fragment {
    private static final String NUM_CURR = "CURRENT";
    private static final String COL_CURR = "COLOR";
    private int number, color;
    private TextView textview = null;


    static FragmentSecond newInstance(int num, int col) {
        FragmentSecond frag = new FragmentSecond();
        Bundle bundle = new Bundle();
        bundle.putInt(NUM_CURR, num);
        bundle.putInt(COL_CURR, col);
        frag.setArguments(bundle);
        return frag;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        Bundle arguments = getArguments();
        if(savedInstanceState != null) {
            number = savedInstanceState.getInt(NUM_CURR);
            color = savedInstanceState.getInt(COL_CURR);
        }
        if(arguments != null) {
            number = arguments.getInt(NUM_CURR);
            color = arguments.getInt(COL_CURR);
        }
        textview = view.findViewById(R.id.textview_num);
        textview.setText(String.valueOf(number));
        textview.setTextColor(color);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        textview = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NUM_CURR, number);
        outState.putInt(COL_CURR, color);
    }
}
