package com.github.aidan.java8.test.design.decorator;

/**
 *
 * @author wang yi fei
 * @date 2019/5/22 14:44
 *
 */
// 装饰类
public abstract class Border extends Display{
	protected Display display;  // 表示被装饰物
	protected Border(Display display) {
		this.display = display;
	}
}
