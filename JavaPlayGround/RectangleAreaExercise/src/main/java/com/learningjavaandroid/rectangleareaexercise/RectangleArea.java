package com.learningjavaandroid.rectangleareaexercise;

import java.lang.Math; // to use abs function

public class RectangleArea {
    public static void main(String[] args) {
        System.out.println(rectArea(-3,4));
    }

    public static int rectArea(int width, int length) {
        return Math.abs(length * width);
    }
}