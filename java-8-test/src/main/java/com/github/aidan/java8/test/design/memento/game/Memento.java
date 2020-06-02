package com.github.aidan.java8.test.design.memento.game;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wang yi fei
 * @date 2019/5/30 13:43
 */
public class Memento {
	int money;
	ArrayList fruits = new ArrayList();

	public int getMoney() {
		return money;
	}

	Memento(int money) {
		this.money = money;
	}
	void addFruit(String fruit){
		fruits.add(fruit);
	}
	List getFruits(){
		return (List) fruits.clone();
	}

}
