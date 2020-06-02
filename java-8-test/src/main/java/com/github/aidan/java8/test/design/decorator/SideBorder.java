package com.github.aidan.java8.test.design.decorator;

/**
 * @author wang yi fei
 * @date 2019/5/22 14:52
 */
public class SideBorder extends Border {
	private char borderChar; //表示装饰边框的字符

	public SideBorder(Display display, char borderChar) {  // 通过构造函数指定 Display 和装饰边框字符
		super(display);
		this.borderChar = borderChar;
	}

	@Override
	public int getColumns() {  // 字符数为字符串字符数加上 两侧边框字符数
		return 1 + display.getColumns() + 1;
	}

	@Override
	public int getRows() {  // 行数即被装饰物的行数
		return display.getRows();
	}

	@Override
	public String getRowText(int row) {  // 指定的那一行的字符串为被装饰物的字符串 加上两侧的边框字符
		return borderChar+ display.getRowText(row)+ borderChar;
	}
}
