package com.github.aidan.java.design.study.active;

/**
 * @author wang yi fei
 * @date 2020/5/29 10:32
 */
abstract class MethodRequest<T> {
	protected final Servant servant;
	protected final FutureResult<T> future;

	protected MethodRequest(Servant servant, FutureResult<T> future) {
		this.servant = servant;
		this.future = future;
	}

	public abstract void execute();
}
