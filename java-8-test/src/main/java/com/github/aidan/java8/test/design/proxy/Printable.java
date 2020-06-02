package com.github.aidan.java8.test.design.proxy;

/**
 * @author wang yi fei
 * @date 2019/6/12 19:56
 */
public interface Printable {
	void setPrinterName(String name);
	String getPrinterName();
	void print(String string); //   显示打印的文字
}
