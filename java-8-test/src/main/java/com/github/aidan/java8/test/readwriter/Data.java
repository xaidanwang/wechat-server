package com.github.aidan.java8.test.readwriter;

/**
 * @author wang yi fei
 * @date 2019/12/20 15:26
 */
public class Data {
	private final ReadWriteLock lock = new ReadWriteLock();
	private final char[] buffer;

	public Data(int size) {
		this.buffer = new char[size];
		for (int  i = 0; i < buffer.length; i++){
			buffer[i] = '*';
		}
	}
	public char[] read() throws InterruptedException {
		lock.readLock();
		try {
			return doRead();
		}finally {
			lock.readUnlock();
		}
	}
	public void write(char c) throws InterruptedException {
		lock.writeLock();
		try {
			doWrite(c);
		}finally {
			lock.writeUnlock();
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
