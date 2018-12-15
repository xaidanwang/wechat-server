package com.github.aidan.java8.test.lambda;

import com.github.aidan.java8.test.lambda.util.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JavaLambdaTest {

    public static void main(String[] args) {

        final int num = 1;
         Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));

        Test test = sss ->System.out.println(sss);

        s.convert(2);  // 输出结果为 3

       // Converter<Integer, String> s1 = (int param ,int param2) -> System.out.println(param + param2);
       // s1.convert2(1,2);

        String[] arr = {"program", "creek", "is", "a", "java", "site"};
        Arrays.sort(arr, Comparator.comparingInt(String::length));
        Arrays.sort(arr, (String m, String n) -> Integer.compare(m.length(), n.length()));
        System.out.println(Arrays.toString(arr));
        System.out.println("=================================================================");
        new Thread(new Runnable() {
            @Override
            public void run() {

                filterTest();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("111111111111111111111111111111111111111");

                System.out.println("The old runable now is using!");

            }
        }).start();

        System.out.println("=================================================================");
        new Thread(
                ()->{System.out.println("asdfdasfawde"); filterTest();System.out.println("2222222222222222222");}).start();


    //    filterTest();
    }

    public interface Converter<T1, T2> {
        void convert(int i);

       // void convert2(int i,int j);
    }

    public static void filterTest() {
        List<Double> cost = Arrays.asList(10.0, 20.0,30.0,40.0);
        List<Double> filteredCost = cost.stream().filter(x -> x > 25.0).collect(Collectors.toList());
        filteredCost.forEach(x -> System.out.println(x));

    }
}
