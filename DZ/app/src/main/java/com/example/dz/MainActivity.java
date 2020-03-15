package com.example.dz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigator = new Navigator();
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container,   new FragmentFirst());
            transaction.commit();
        }
    }

    public static Navigator getNavigator() {
        return navigator;
    }

    class Navigator implements FragmentNavigator {
        private final FragmentManager fragmentManager = getSupportFragmentManager();
        @Override
        public void navigateToAnotherFragment(int num, int col) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, FragmentSecond.newInstance(num, col)); // same as remove + add
            transaction.addToBackStack(null);
            transaction.commit();// all transactions before commit are added to backstack

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        navigator = null;
    }


}
