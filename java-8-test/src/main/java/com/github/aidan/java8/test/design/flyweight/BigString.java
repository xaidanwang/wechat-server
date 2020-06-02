package com.github.aidan.java8.test.design.flyweight;

/**
 * @author wang yi fei
 * @date 2019/6/12 14:00
 */
public class BigString {

	private BigChar[] bigChars;

	public BigString(String string,boolean shared) {
		if (shared){
			initShared(string);
		}else {
			initUnshared(string);
		}
	}
	//  显示
	public void print(){
		for (int i=0;i < bigChars.length; i++){
			bigChars[i].print();
		}
	}

	private void initShared(String string){
		bigChars = new BigChar[string.length()];
		BigCharFactory factory = BigCharFactory.getInstance();
		for (int i=0;i < bigChars.length; i++){
			bigChars[i] = factory.getBigChar(string.charAt(i));
		}
	}

	private void initUnshared(String string){
		bigChars = new BigChar[string.length()];
		for (int i=0;i < bigChars.length; i++){
			bigChars[i] = new BigChar(string.charAt(i));
		}
	}
}
