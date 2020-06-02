package com.github.aidan.java8.test.design.state;

/**
 * @author wang yi fei
 * @date 2019/6/11 13:54
 */
public class Main {

	public static void main(String[] args) {
		SafeFrame safeFrame  = new SafeFrame("State Sample");
		while (true){
			for (int hour = 0; hour < 24; hour++){
				safeFrame.setClock(hour);
				try {
					Thread.sleep(1000);
				}catch (Exception e){
				}
			}
		}
	}
}
