package com.github.aidan.java8.test.run;

/**
 * @author wang yi fei
 * @date 2019/11/25 19:52
 */
public class Bank {

	private int money;
	private String name;

	public Bank(int money, String name) {
		this.money = money;
		this.name = name;
	}

	public void deposite(int m){
		money += m;
	}

	public boolean withdraw(int m){
		if (money >= m){
			money -= m;
			check();
			return true;
		}else {
			return false;
		}
	}

	private void check(){
		if (money < 0){
			System.out.println("可用余额为负数! money = " + money);
		}
	}
}
