package com.github.aidan.java8.test.lambda;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wang yi fei
 * @date 2019/12/4 20:14
 */
public class List {

	public static void main(String[] args) {
		Stream.of("one", "two", "three", "four")
				         .filter(e -> e.length() > 3)
				         .peek(e -> System.out.println("Filtered value: " + e))
				         .map(String::toUpperCase)
				        .peek(e -> System.out.println("Mapped value: " + e))
				       .collect(Collectors.toList());
	}
}
