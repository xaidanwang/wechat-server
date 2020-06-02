package com.github.aidan.java.design.study.future.thread;

/**
 * @author wang yi fei
 * @date 2020/5/15 16:30
 */
public class ReadlData implements Data{
	private final String content;

	public ReadlData(int count,char c) {
		System.out.println(" makding ReadData(" + count + ", " + c + ") BEGIN");
		char[] buffre = new char[count];
		for (int i = 0; i < buffre.length; i++) {
			buffre[i] = c;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(" makding ReadData(" + count + ", " + c + ") END");
		this.content = new String(buffre);
	}


	@Override
	public synchronized String getContent() {
		return content;
	}
}
