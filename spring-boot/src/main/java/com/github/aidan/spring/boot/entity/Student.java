package com.github.aidan.spring.boot.entity;

/**
 * @author wang yi fei
 * @date 2019/3/27 13:08
 */
public class Student {

    private String math;

    private String chiness;

    public Student(String math, String chiness) {
        this.math = math;
        this.chiness = chiness;
    }

    public String getMath() {
        return math;
    }

    public String getChiness() {
        return chiness;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public void setChiness(String chiness) {
        this.chiness = chiness;
    }
}
