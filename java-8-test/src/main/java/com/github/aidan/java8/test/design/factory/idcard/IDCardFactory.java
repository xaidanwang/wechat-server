package com.github.aidan.java8.test.design.factory.idcard;

import com.github.aidan.java8.test.design.factory.framework.Factory;
import com.github.aidan.java8.test.design.factory.framework.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author wang yi fei
 * @date 2019/5/13 11:17
 */
public class IDCardFactory extends Factory {

	private List owners = new ArrayList();
	private int serial = 100;

	private HashMap database = new HashMap();
	@Override
	protected Product createProduct(String owner) {
		return new IDCard(owner,serial++);
	}

	@Override
	protected void registerProduct(Product product) {
		// owners.add(((IDCard)product).getOwner());
		IDCard idCard = (IDCard)product;
		database.put(idCard.getSerial(),idCard.getOwner());
	}

	public List getOwners(){
		return owners;
	}

	public int getSerial() {
		return serial;
	}

	public HashMap getDatabase() {
		return database;
	}
}
