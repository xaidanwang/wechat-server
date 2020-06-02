package com.github.aidan.java8.test.run;

/**
 * @author wang yi fei
 * @date 2019/11/25 20:00
 */
public class ClientThread extends Thread {

	private Bank bank;

	public ClientThread(Bank bank){
		this.bank =  bank;
	}

	@Override
	public synchronized void run() {
		while (true){
			boolean ok = bank.withdraw(1000);
			if (ok){
				bank.deposite(1000);
			}
		}
	}

	public static void main(String[] args) {
		Bank bank = new Bank(1000,"A Bad Bank");
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		Thread t = new ClientThread(bank);

	}
}
