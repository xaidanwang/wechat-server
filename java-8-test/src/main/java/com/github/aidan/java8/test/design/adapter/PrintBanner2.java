package com.github.aidan.java8.test.design.adapter;

/**
 * @author wang yi fei
 * @date 2019/5/11 10:21
 */
public class PrintBanner2 extends Print2{

	private Banner banner;
	public PrintBanner2(String string) {
		this.banner =  new Banner(string);
	}

	@Override
	public void printWeak() {
		banner.showWithParen();
	}

	@Override
	public void printStrong() {
		banner.showWithAster();
	}
}
