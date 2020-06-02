package com.github.aidan.java8.test.test;

import cn.hutool.core.util.IdcardUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author wang yi fei
 * @date 2019/9/3 14:15
 */
@Data
public class Order2 {
	private static final DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyyMMdd");
	private String name;
	private String age;

	public static void main(String[] args) {
//		String credentialNo = "341281199401223475";
//		String birthDay = IdcardUtil.getBirth(credentialNo);
//		birthDay = LocalDate.parse(birthDay,dtf2).toString();
//		System.out.println(birthDay);
		Order2 order2 = new Order2();
		System.out.println(order2.age);
		order2.test("15");
		System.out.println(order2.age);
		order2.test1("18");
		System.out.println(order2.age);
		System.out.println(order2.age);
	}

	public void test(final String age){
		this.age = "26";
	}
	public void test1(final String age){
		System.out.println(age);
	}
}
