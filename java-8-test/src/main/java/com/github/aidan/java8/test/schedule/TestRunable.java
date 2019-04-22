package com.github.aidan.java8.test.schedule;

/**
 * @author wang yi fei
 * @date 2019/3/30 10:36
 */
public class TestRunable implements Runnable {


    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName()+"TestRunable 定时任务开始执行");

        return;
    }
}
