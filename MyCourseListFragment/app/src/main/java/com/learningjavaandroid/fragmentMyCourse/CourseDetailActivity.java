package com.learningjavaandroid.fragmentMyCourse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class CourseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        if(savedInstanceState == null){
            CourseDetailFragment fragment = new CourseDetailFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().add(R.id.detailContainer,fragment).commit();
        }
    }
}