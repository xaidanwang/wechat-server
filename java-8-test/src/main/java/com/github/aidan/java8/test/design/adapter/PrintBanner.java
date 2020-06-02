package com.github.aidan.java8.test.design.adapter;

/**
 * @author wang yi fei
 * @date 2019/5/11 10:21
 */
public class PrintBanner extends Banner implements Print {

	public PrintBanner(String string) {
		super(string);
	}

	@Override
	public void printWeak() {
		showWithParen();
	}

	@Override
	public void printStrong() {
		showWithAster();
	}
}
