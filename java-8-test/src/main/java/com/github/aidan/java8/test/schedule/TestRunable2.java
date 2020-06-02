package com.github.aidan.java8.test.schedule;

/**
 * @author wang yi fei
 * @date 2019/3/30 10:44
 */
public class TestRunable2 extends Thread{

    public TestRunable2(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"TestRunable2 定时任务开始执行");
    }


    public static void main(String[] args) {

        for (int i=0;i<3;i++){

            test(i);
            System.out.println("===============");
        }
    }


    private static void test(int i){
        System.out.println("第 "+i+"次输出");
        return;
    }
}
