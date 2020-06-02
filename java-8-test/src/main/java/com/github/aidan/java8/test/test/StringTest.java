package com.github.aidan.java8.test.test;

import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author wang yi fei
 * @date 2019/4/28 16:32
 */
public class StringTest {

	public static void main(String[] args) {


/*		Order order = new Order();
		String age[]  = {"1","2","3"};
		order.setAge(age);
		order.setName("wang");

		Order2 order2 = new Order2();
		BeanUtils.copyProperties(order,order2);

		System.out.println(order);
		System.out.println(order2);*/
		String a= "341281199401223475";
		System.out.println(a.substring(6,14));
		LocalDate.parse(a.substring(6,14), DateTimeFormatter.ofPattern("yyyyMMdd"));
		System.out.println(LocalDate.parse(a.substring(6,14), DateTimeFormatter.ofPattern("yyyyMMdd")));
	}
}
