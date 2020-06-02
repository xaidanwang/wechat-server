package com.github.aidan.java8.test.design.memento.game;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * @author wang yi fei
 * @date 2019/5/30 14:37
 */
public class Gamer {
	private int money;
	private List fruits = new ArrayList();
	private Random random = new Random();
	private static String[] fruitsname = {"苹果","葡萄","香蕉","橘子"};

	public Gamer(int money) {
		this.money = money;
	}

	public int getMoney() {
		return money;
	}

	public void bet(){
		int dice = random.nextInt(6)+1;
		if (dice == 1){
			money += 100;
			System.out.println("所持金钱增加了。");
		}else if (dice == 2){
			money /= 2;
			System.out.println("所持今天减半了");
		}else if (dice == 6){
			String f = getFruit();
			System.out.println("获得了水果 ( " + f + " )。");
			fruits.add(f);
		}else {
			System.out.println("什么都没有发生。");
		}
	}

	public Memento createMemento(){
		Memento m = new Memento(money);
		Iterator it = fruits.iterator();
		while (it.hasNext()){
			String f = (String)it.next();
			if (f.startsWith("好吃的")){
				m.addFruit(f);
			}
		}
		return m;
	}

	public void restoreMemento(Memento memento){
		this.money = memento.money;
		this.fruits = memento.fruits;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("money", money)
				.append("fruits", fruits)
				.toString();
	}

	private String getFruit(){
		String prefix ="";
		if (random.nextBoolean()){
			prefix = "好吃的";
		}
		return prefix + fruitsname[random.nextInt(fruitsname.length)];
	}
}
