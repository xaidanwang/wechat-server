package com.github.aidan.spring.boot.entity;

import java.util.Map;

/**
 * @author wang yi fei
 * @date 2019/3/27 13:07
 */
public class User {

    private String name;

    private int age;

    private Map<String,Student> studentMap;


    public User(String name, int age, Map<String, Student> studentMap) {
        this.name = name;
        this.age = age;
        this.studentMap = studentMap;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Map<String, Student> getStudentMap() {
        return studentMap;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setStudentMap(Map<String, Student> studentMap) {
        this.studentMap = studentMap;
    }
}
