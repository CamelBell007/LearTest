package learn.vincent.com.learntest.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/23.
 */
public class Student {

    private String name;
    private int age;
    private ArrayList<Course> courses;
    public Student(String name,int age,ArrayList<Course> courses){
        this.age = age;
        this.name = name;
        this.courses = courses;
    }


    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
}
