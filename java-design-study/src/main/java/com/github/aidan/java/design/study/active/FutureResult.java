package com.github.aidan.java.design.study.active;

/**
 * @author wang yi fei
 * @date 2020/5/29 10:56
 */
public class FutureResult<T> extends Result<T> {
	private Result<T> result;
	private boolean ready = false;
	public synchronized void setResult(Result<T> result){
		this.result = result;
		this.ready = true;
		notifyAll();
	}

	@Override
	public synchronized T getResultValue() {
		while (!ready){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return result.getResultValue();
	}
}
