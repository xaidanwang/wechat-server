package com.github.aidan.java8.test.design.builder;

/**
 * @author wang yi fei
 * @date 2019/5/15 14:47
 */
public class TextBuilder extends Builder {
	private StringBuffer buffer = new StringBuffer();
	public String getResult(){
		return buffer.toString();
	}
	@Override
	protected void buildTitle(String title) {
		buffer.append("===================================\n");
		buffer.append("["+ title+ "]\n");
		buffer.append("\n");
	}
	@Override
	protected void buildString(String str) {
		buffer.append("■"+str+"\n");
		buffer.append("\n");
	}
	@Override
	protected void buildItems(String[] items) {

		for (int i=0;i<items.length; i++){
			buffer.append("  ▪"+items[i] + "\n");
		}
		buffer.append("\n");
	}
	@Override
	protected void buildDone() {
		buffer.append("===================================\n");
	}
}
