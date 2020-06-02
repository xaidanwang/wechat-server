package com.github.aidan.java8.test.design.decorator;

/**
 * @author wang yi fei
 * @date 2019/5/22 14:11
 */

// 被装饰物
public abstract class Display {
	public abstract int getColumns(); // 获取横向字符数
	public abstract  int getRows(); // 获取纵向行数
	public abstract String getRowText(int row);  // 获取第 row 行的字符串
	public final void show(){
		for (int i= 0 ; i < getRows(); i++){
			System.out.println(getRowText(i));
		}
	}


}
