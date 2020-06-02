package com.github.aidan.java8.test.design.decorator.practice;

import com.github.aidan.java8.test.design.decorator.Display;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wang yi fei
 * @date 2019/5/24 13:41
 */
public class MultiStringDisplay extends Display {

	private List<String> arrayList = new ArrayList();
	private int columns = 0; // 最大字符数
	public MultiStringDisplay(String str) {
		add(str);
	}
	public MultiStringDisplay() {
	}
	@Override
	public int getColumns() {
		return columns;
	}

	@Override
	public int getRows() {
		return arrayList.size();
	}

	@Override
	public String getRowText(int row) {
		return arrayList.get(row);
	}

	public void add(String string){
		arrayList.add(string);
		updateColumns(string);
	}

	private void updateColumns(String string){
		int newColumns = string.getBytes().length;
		if ( newColumns > columns){
			columns = newColumns;
		}
	}
}
