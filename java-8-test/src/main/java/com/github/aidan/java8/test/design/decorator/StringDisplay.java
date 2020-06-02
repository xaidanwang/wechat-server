package com.github.aidan.java8.test.design.decorator;

/**
 * @author wang yi fei
 * @date 2019/5/22 14:15
 */
public class StringDisplay extends Display{
	private String string; // 要显示的字符串

	public StringDisplay(String string) {
		this.string = string;
	}

	@Override
	public int getColumns() {   // 字符数
		return string.getBytes().length;
	}

	@Override
	public int getRows() {  // 行数
		return 1;
	}

	@Override
	public String getRowText(int row) {  // 仅当 row 为 0 时返回值
		if (row == 0){
			return  string;
		}else {
			return null;
		}
	}
}
