package com.github.aidan.java8.test.design.bridge;

/**
 * @author wang yi fei
 * @date 2019/5/18 14:47
 */
public class CountDisplay extends Display{
	public CountDisplay(DisplayImpl impl) {
		super(impl);
	}

	public void multiDisplay(int times){
		open();
		for (int i=0;i <times;i++){
			print();
		}
		close();
	}
}
