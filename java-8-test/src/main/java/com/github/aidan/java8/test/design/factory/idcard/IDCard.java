package com.github.aidan.java8.test.design.factory.idcard;

import com.github.aidan.java8.test.design.factory.framework.Product;

/**
 * @author wang yi fei
 * @date 2019/5/13 11:15
 */
public class IDCard extends Product {
	private String owner;
	private int serial;
	@Override
	public void use() {
		System.out.println("使用 "+ owner+"("+serial+")" + "的ID 卡");
	}

	public IDCard() {
	}

	/**
	 * @param owner
	 */
	//  构造函数不是public的 ，这样使用可以让 idcard包外的类，无法通过new 关键字来创建IDCard 类的实例。
	// 这样就可以强迫外部必须通过 IDCardFactory 来生成 IDCard 的实例
	IDCard(String owner, int serial) {
		System.out.println("制作 "+ owner +"("+serial+")" + "的ID 卡");
		this.owner = owner;
		this.serial = serial;
	}

	public String getOwner() {
		return owner;
	}

	public int getSerial() {
		return serial;
	}
}
