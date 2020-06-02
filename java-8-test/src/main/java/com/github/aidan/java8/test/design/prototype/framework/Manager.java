package com.github.aidan.java8.test.design.prototype.framework;

import java.util.HashMap;

/**
 * @author wang yi fei
 * @date 2019/5/13 16:49
 */
public class Manager {
	private HashMap showcase = new HashMap();
	public void register(String name, Product proto){
		showcase.put(name,proto);
	}

	public Product create(String protoname){
		Product p = (Product) showcase.get(protoname);
		return p.createClone();
	}
}
