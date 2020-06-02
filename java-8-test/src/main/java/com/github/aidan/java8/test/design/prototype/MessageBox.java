package com.github.aidan.java8.test.design.prototype;

/**
 * @author wang yi fei
 * @date 2019/5/13 17:07
 */
public class MessageBox extends AbstractProduct {

	private char decochar;

	public MessageBox(char decochar) {
		this.decochar = decochar;
	}

	@Override
	public void use(String s) {
		int length = s.getBytes().length;
		for (int i=0;i < length + 4; i++){
			System.out.print(decochar);
		}
		System.out.println("");
		System.out.println(decochar + " "+ s + " " + decochar);
		for (int i=0;i < length + 4; i++){
			System.out.print(decochar);
		}
		System.out.println("");
	}
}
