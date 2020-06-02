package com.github.aidan.java8.test.design.decorator.practice;

import com.github.aidan.java8.test.design.decorator.Border;
import com.github.aidan.java8.test.design.decorator.Display;

/**
 * @author wang yi fei
 * @date 2019/5/24 10:47
 */
public class UpDownBorder extends Border {

	private char borderChar;
	public UpDownBorder(Display display, char borderChar) {
		super(display);
		this.borderChar = borderChar;
	}

	@Override
	public int getColumns() {
		return display.getColumns();
	}

	@Override
	public int getRows() {
		return 1+display.getRows()+1;
	}

	@Override
	public String getRowText(int row) {
		if (row == 0){
			return   makeLine(display.getColumns());  // 上边框
		}else if (row == display.getRows() +1){
			return  makeLine(display.getColumns());  //下边框
		}else {
			return  display.getRowText(row-1);  // 其他边框
		}
	}

	private String makeLine(int count){
		StringBuffer buf = new StringBuffer();
		for (int i=0;i < count; i++){
			buf.append(borderChar);
		}
		return buf.toString();
	}
}
