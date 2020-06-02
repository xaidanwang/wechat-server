package com.github.aidan.java8.test.design.interpreter;

import java.util.Scanner;

/**
 * @author wang yi fei
 * @date 2019/6/14 17:13
 */
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (true){
			String input = scan.nextLine();
			System.out.println(" text = \"" + input + "\"");
			Node node = new ProgramNode();
			try {
				node.parse(new Context(input));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			System.out.println("node = " +node);
		}
	}
}
