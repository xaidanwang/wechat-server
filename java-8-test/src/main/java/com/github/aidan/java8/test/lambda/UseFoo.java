package com.github.aidan.java8.test.lambda;

import com.github.aidan.java8.test.lambda.util.Foo;

import java.util.function.Function;

public class UseFoo {


    public static void main(String[] args) {

        Foo foo = parameter -> parameter + " from lambda";


        String result = add("Message ", parameter -> parameter + " from lambda");

        System.out.println(result);

        Foo fooByIC = new Foo(){

            @Override
            public String method(String string) {
                return null;
            }
        };

        Function<Integer, String> fn =
                parameter -> parameter + " from lambda 1";
        String result1 = add1("Message ", fn);

        System.out.println(result1);
    }

    public static String add(String string, Foo foo) {
        return foo.method(string);
    }

    public static String add1(String string, Function<Integer, String> fn) {

        return fn.apply(1);

    }
}
