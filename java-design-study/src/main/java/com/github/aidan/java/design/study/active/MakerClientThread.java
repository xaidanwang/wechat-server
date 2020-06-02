package com.github.aidan.java.design.study.active;

/**
 * @author wang yi fei
 * @date 2020/5/28 10:51
 */
public class MakerClientThread extends Thread {
	private ActiceObject acticeObject;
	private final char fillchar;

	public MakerClientThread(String name, ActiceObject acticeObject) {
		super(name);
		this.acticeObject = acticeObject;
		this.fillchar = name.charAt(0);
	}

	@Override
	public void run() {
		try {
			for (int i = 0; true ; i++) {
				// 有返回值的调用
				Result<String> result = acticeObject.makeString(i,fillchar);
				Thread.sleep(10);
				String value = result.getResultValue();
				System.out.println(Thread.currentThread().getName() + ": value = " + value);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
