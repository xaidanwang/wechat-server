package com.github.aidan.java8.test.run;

/**
 * @author wang yi fei
 * @date 2019/12/28 17:32
 */
public class Main {

	public static void main(String[] args) {
		Chidren a1 = new Chidren();
		Chidren a2 = new Chidren();

		a1.setA(true);
		System.out.println(a1.isA());
		System.out.println(a2.isA());
		a2.setA(true);
		System.out.println(a1.isA());
		System.out.println(a2.isA());

		String a ="wang";
		if (a.equalsIgnoreCase(null)){
			System.out.println("==============");
		}
		System.out.println("aaaaaaaaaaaaaa");
	}
}
