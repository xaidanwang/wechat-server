package com.github.aidan.java8.test.design.chain;

/**
 * @author wang yi fei
 * @date 2019/5/25 15:07
 */
public class Trouble {
	private int number;   // 问题编号

	public Trouble(int number) {  //  生成问题
		this.number = number;
	}


	public int getNumber() {  // 获取问题编号
		return number;
	}

	@Override
	public String toString() {  //  代表问题编号字符串
		return "[Trouble " + number + "]";

	}
}
