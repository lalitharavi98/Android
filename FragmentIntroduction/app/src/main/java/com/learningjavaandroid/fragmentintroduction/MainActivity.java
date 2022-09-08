package com.learningjavaandroid.fragmentintroduction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        // get the container id to which we are adding the fragment
        Fragment fragment = fragmentManager.findFragmentById(R.id.myContainer);
        if(fragment == null){
            fragment = new MainFragment();
            fragmentManager.beginTransaction().add(R.id.myContainer, fragment)
                    .commit();
        }
    }
}