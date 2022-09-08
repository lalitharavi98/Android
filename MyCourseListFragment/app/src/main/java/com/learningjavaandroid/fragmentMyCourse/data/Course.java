package com.learningjavaandroid.fragmentMyCourse.data;

import android.content.Context;

public class Course {
    private String courseName;
    private String courseImage;

    public Course() {
    }

    public Course(String courseName, String courseImage) {
        this.courseName = courseName;
        this.courseImage = courseImage;
    }

    public int getImageResourceId (Context context) {
        return context.getResources().getIdentifier(this.courseName.replace(" ","").toLowerCase(),"drawable",context.getPackageName());
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(String courseImage) {
        this.courseImage = courseImage;
    }
}
