package com.github.aidan.java8.test.design.prototype;

import com.github.aidan.java8.test.design.prototype.framework.Product;

/**
 * @author wang yi fei
 * @date 2019/5/15 13:45
 */
public abstract class AbstractProduct implements Product{

	@Override
	public Product createClone() {
		Product p = null;

		try {
			p = (Product)this.clone();
		}catch (CloneNotSupportedException c){
			c.printStackTrace();
		}
		return p;
	}
}
