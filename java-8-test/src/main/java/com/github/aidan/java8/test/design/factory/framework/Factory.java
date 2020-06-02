package com.github.aidan.java8.test.design.factory.framework;

/**
 * @author wang yi fei
 * @date 2019/5/13 11:06
 */
public abstract class Factory {
	public final Product create(String owner){
		Product p = createProduct(owner);
		registerProduct(p);
		return p;
	}

	protected abstract Product createProduct(String owner);
	protected abstract void registerProduct(Product product);

}
