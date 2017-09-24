package com.u9time.myapplication;

/**
 * Created by Lenovo on 2017/8/14.
 */

public class Student {
    String name;
    int age;
    boolean isAdu;

    public boolean isAdu() {
        return isAdu;
    }

    public void setAdu(boolean adu) {
        isAdu = adu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
