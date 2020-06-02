package com.github.aidan.java.design.study.active;

/**
 * @author wang yi fei
 * @date 2020/5/28 16:35
 * 最终指定方法的类
 */
public class Servant implements ActiceObject{

	public Result<String> makeString(int count,char fillchar){
		char[] buffer =  new char[count];
		for (int i = 0; i < count; i++) {
			buffer[i] = fillchar;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return new RealResult<String>(new String(buffer));
	}

	@Override
	public void displayString(String string) {
		try {
			System.out.println("displayString: " + string);
			Thread.sleep(10);
		}catch (Exception e){

		}
	}
}
