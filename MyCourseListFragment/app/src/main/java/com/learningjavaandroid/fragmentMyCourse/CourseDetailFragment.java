package com.learningjavaandroid.fragmentMyCourse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.learningjavaandroid.fragmentMyCourse.data.Course;

public class CourseDetailFragment extends Fragment {
    Course course;

    public CourseDetailFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_detail_fragment,container,false);
        if(course != null) {
            TextView courseName = view.findViewById(R.id.detailCourseName);
            courseName.setText(course.getCourseName());

             ImageView courseImageView = view.findViewById(R.id.detailCourseImage);
             courseImageView.setImageResource(course.getImageResourceId(getActivity()));

            TextView courseDesc = view.findViewById(R.id.detailsCourseDescription);
            courseDesc.setText(course.getCourseName());
        }
        return view;
    }
}
