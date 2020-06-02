package com.github.aidan.java8.test.producer.consumer;

/**
 * @author wang yi fei
 * @date 2019/12/13 14:19
 */
public class Table {
	private final String[] buffer;
	private int tail;   // 下次put 的位置
	private int head;   // 下次take 的位置
	private int count;  // buffer 中的蛋糕个数

	public Table(int count) {
		this.buffer = new String[count];
		this.count = count;
		this.head = 0;
		this.tail = 0;
		this.count = 0;
	}

	// 放置蛋糕
	public synchronized void put(String cake) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " puts " + cake);
		while (count >= buffer.length){
			System.out.println("1111111111111111111");
			wait();
		}
		buffer[tail] = cake;
		tail = (tail + 1) % buffer.length;
		count++;
		notifyAll();
	}
	// 拿取蛋糕
	public synchronized String take() throws InterruptedException {
		while (count <= 0){
			wait();
		}
		String cake = buffer[head];
		head = (head + 1) % buffer.length;
		count--;
		notifyAll();
		System.out.println(Thread.currentThread().getName() + " takes " + cake);
		return cake;
	}

	public synchronized void clear(){
		for (int i =0; i<buffer.length; i++){
			buffer[i] = null;
		}
		count = 0;
		head = 0;
		tail = 0;
		notifyAll();
	}
}
