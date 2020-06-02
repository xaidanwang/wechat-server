package com.github.aidan.java.design.study.active;

/**
 * @author wang yi fei
 * @date 2020/5/28 11:01
 */
public class DisplayClientThread extends Thread {

	private final ActiceObject acticeObject;

	public DisplayClientThread(String name, ActiceObject acticeObject) {
		super(name);
		this.acticeObject = acticeObject;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; true ; i++) {
				// 没有返回值
				String string = Thread.currentThread().getName() + " " + i;
				acticeObject.displayString(string);
				Thread.sleep(200);
			}
		}catch (Exception e){
		}
	}
}
