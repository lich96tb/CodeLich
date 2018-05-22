package com.example.cntt.myapplication;

/**
 * Created by CNTT on 3/22/2018.
 */

public class Student {
    private String name;
    private int year_old;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear_old() {
        return year_old;
    }

    public void setYear_old(int year_old) {
        this.year_old = year_old;
    }

    public Student(String name, int year_old) {
        this.name = name;
        this.year_old = year_old;
    }
}
