package com.learningjavaandroid.fragmentMyCourse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Toast;

import com.learningjavaandroid.fragmentMyCourse.data.Course;

public class MainActivity extends AppCompatActivity implements CourseListFragment.Callbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FragmentManager fragmentManager= getSupportFragmentManager();
//        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.myContainer);
//        if(fragment == null) {
//            fragment = new course_list_fragment();
//            fragmentManager.beginTransaction().add(R.id.myContainer,fragment).commit();
//
//        }
    }

    @Override
    public void onItemSelected(Course course) {
        Toast.makeText(this,"Helloooo",Toast.LENGTH_SHORT).show();
    }
}