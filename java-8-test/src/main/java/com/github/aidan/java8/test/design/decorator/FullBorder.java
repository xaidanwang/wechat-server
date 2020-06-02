package com.github.aidan.java8.test.design.decorator;

/**
 * @author wang yi fei
 * @date 2019/5/22 15:20
 */
public class FullBorder extends Border {
	public FullBorder(Display display) {
		super(display);
	}

	@Override
	public int getColumns() {
		return 1 +display.getColumns()+ 1;
	}

	@Override
	public int getRows() {
		return 1+display.getRows() +1;
	}

	@Override
	public String getRowText(int row) {
		if (row == 0){
			return "+" + makeLine('-',display.getColumns())+ "+";  // 上边框
		}else if (row == display.getRows() +1){
			return "+" + makeLine('-',display.getColumns())+ "+";  //下边框
		}else {
			return "|" + display.getRowText(row-1) + "|";  // 其他边框
		}
	}

	private String makeLine(char ch,int count){
		StringBuffer buf = new StringBuffer();
		for (int i=0;i < count; i++){
			buf.append(ch);
		}
		return buf.toString();
	}
}
