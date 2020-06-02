package com.github.aidan.java8.test.balking;

/**
 * @author wang yi fei
 * @date 2019/12/2 20:05
 */
public class SaverThread extends Thread {
	private final Data data;

	public SaverThread(String name, Data data) {
		super(name);
		this.data = data;
	}

	@Override
	public void run() {
		try {

			while (true){
				data.save();
				Thread.sleep(1000);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
