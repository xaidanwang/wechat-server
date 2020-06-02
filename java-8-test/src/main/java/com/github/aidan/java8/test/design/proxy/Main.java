package com.github.aidan.java8.test.design.proxy;

/**
 * @author wang yi fei
 * @date 2019/6/12 20:38
 */
public class Main {

	public static void main(String[] args) {
		Printable p =new PrinterProxy("Alice","");
		System.out.println("现在的名字是" + p.getPrinterName() + "。");
		p.setPrinterName("Bob");
		System.out.println("现在的名字是" + p.getPrinterName() + "。");
		p.print("Hell, world.");
	}
}
