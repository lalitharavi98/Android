package com.learningjavaandroid.fragmentMyCourse;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.learningjavaandroid.fragmentMyCourse.Util.ScreenUtility;
import com.learningjavaandroid.fragmentMyCourse.data.Course;
import com.learningjavaandroid.fragmentMyCourse.data.CourseArrayAdapter;
import com.learningjavaandroid.fragmentMyCourse.data.CourseData;

import java.util.List;

public class CourseListFragment extends ListFragment {
    List<Course> courses = new CourseData().courseList();
    public CourseListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScreenUtility screenUtility = new ScreenUtility(getActivity());

        Log.d("WIDTH", "width " + String.valueOf(screenUtility.getDpWidth()));

        CourseArrayAdapter courseArrayAdapter = new CourseArrayAdapter(getActivity(),
                R.layout.course_listitem,courses);
         setListAdapter(courseArrayAdapter);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_list_fragment,container,false);
        return view;
    }

    public interface Callbacks {
        public void onItemSelected(Course course);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Course course = courses.get(position);

    }
}
