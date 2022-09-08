package com.learningjavaandroid.divideandmultiplynumexercise;

public class DivideAndMultiplyNum {

    //command n

    public DivideAndMultiplyNum() {
    }

    public static void main(String[] args) {
        System.out.println("Division: " + divideNumbers(3,5));
        System.out.println("Multiplication: " + multiplyNumbers(4,6));

    }
    public static double divideNumbers(int firstNumber,int secondNumber) {
        return (double) firstNumber/secondNumber;
    }
    public static int multiplyNumbers(int firstNumber,int secondNumber) {
        return firstNumber*secondNumber;
    }
}