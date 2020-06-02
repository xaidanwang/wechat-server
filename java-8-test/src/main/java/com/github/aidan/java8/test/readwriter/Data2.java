package com.github.aidan.java8.test.readwriter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wang yi fei
 * @date 2019/12/20 15:26
 */
public class Data2 {

	private final ReadWriteLock lock = new ReentrantReadWriteLock(true);
	private final Lock readLock = lock.readLock();
	private final Lock writeLock = lock.writeLock();
	private final char[] buffer;

	public Data2(int size) {
		this.buffer = new char[size];
		for (int  i = 0; i < buffer.length; i++){
			buffer[i] = '*';
		}
	}
	public char[] read() throws InterruptedException {
		readLock.lock();
		try {
			return doRead();
		}finally {
			readLock.unlock();
		}
	}
	public void write(char c) throws InterruptedException {
		writeLock.lock();
		try {
			doWrite(c);
		}finally {
			writeLock.unlock();
		}
	}
	private char[] doRead(){
		char[] newbuf = new char[buffer.length];
		// 数组内容的复制操作通常使用 java.lang.System.arraycopy();
		for (int i = 0; i < buffer.length; i++){
			newbuf[i] = buffer[i];
		}
		slowly();
		return newbuf;
	}
	private void doWrite(char c){
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = c;
			slowly();
		}
	}
	private void slowly(){
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
