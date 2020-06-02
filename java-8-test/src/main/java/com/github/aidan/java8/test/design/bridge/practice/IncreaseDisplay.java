package com.github.aidan.java8.test.design.bridge.practice;

import com.github.aidan.java8.test.design.bridge.CountDisplay;
import com.github.aidan.java8.test.design.bridge.DisplayImpl;

/**
 * @author wang yi fei
 * @date 2019/5/18 19:48
 */
public class IncreaseDisplay extends CountDisplay {

	private int step;

	public IncreaseDisplay(DisplayImpl impl, int step) {
		super(impl);
		this.step = step;
	}

	public void increaseDisplay(int level){
		int count = 0;
		for (int i =0;i<level;i++){
			multiDisplay(count);
			count += step;
		}
	}

	public static void main(String[] args) {
		IncreaseDisplay d1 = new IncreaseDisplay(new CharDisplayImpl('<','*','>'),1);
		IncreaseDisplay d2 = new IncreaseDisplay(new CharDisplayImpl('|','#','-'),2);
		d1.increaseDisplay(4);
		d2.increaseDisplay(6);
	}
}
