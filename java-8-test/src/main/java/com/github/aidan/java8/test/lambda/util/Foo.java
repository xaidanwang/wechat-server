package com.github.aidan.java8.test.lambda.util;

@FunctionalInterface
public interface Foo {


    String method(String string);

    default void defaultMethod() {

        method("11111");

        System.out.println("调用 FOO default 方法");
    }
}
