package com.github.aidan.java8.test.readwriter;

/**
 * @author wang yi fei
 * @date 2019/12/20 15:27
 */
public class ReadWriteLock {
	// (A)..实际正在读取中的线程个数
	private int readingReaders = 0;
	// (B)..正在等待写入的线程个数
	private int waitingWriters = 0;
	// (C)..实际正在写入的线程个数
	private int writingWrites = 0;
	// 若写入优先,则为true
	private boolean preferWriter = true;

	public synchronized void readLock() throws InterruptedException {
		while (writingWrites > 0 || (preferWriter && waitingWriters > 0)){
			wait();
		}
		readingReaders++;
	}
	public synchronized void readUnlock(){
		readingReaders--;
		preferWriter = true;
		notifyAll();
	}
	public synchronized void writeLock() throws InterruptedException {
		try {
			waitingWriters++;
			while (readingReaders > 0 || writingWrites > 0){
				wait();
			}
		}finally {
			waitingWriters--;
		}
		writingWrites++;
	}
	public synchronized void writeUnlock(){
		writingWrites--;
		preferWriter = false;
		notifyAll();
	}
}
