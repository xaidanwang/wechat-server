package com.github.aidan.java8.test.design.decorator.practice;

import com.github.aidan.java8.test.design.decorator.Display;
import com.github.aidan.java8.test.design.decorator.FullBorder;
import com.github.aidan.java8.test.design.decorator.SideBorder;
import com.github.aidan.java8.test.design.decorator.StringDisplay;

/**
 * @author wang yi fei
 * @date 2019/5/24 13:13
 */
public class Main {

	public static void main(String[] args) {
/*		Display d1 = new StringDisplay("Hello, World.");
		Display d2 = new UpDownBorder(d1,'-');
		Display d3 = new SideBorder(d2,'*');
		d1.show();
		d2.show();
		d3.show();
		Display d4 = new FullBorder(new UpDownBorder(new SideBorder(new UpDownBorder(new SideBorder(new StringDisplay("你好世界。"),'*'),'='),'|'),'/'));
		d4.show();*/

		MultiStringDisplay d1 = new MultiStringDisplay();
		d1.add("早上好");
		d1.add("中午好");
		d1.add("晚上好");
		d1.show();

		Display d2 = new SideBorder(d1,'#');
		d2.show();

		Display d3 = new FullBorder(d2);
		d3.show();

		Display d4 = new FullBorder(d1);
		d4.show();
	}
}
