package com.github.aidan.java.design.study.active;

/**
 * @author wang yi fei
 * @date 2020/5/28 16:37
 */
public class RealResult<T> extends Result<T> {
	private final T resultValue;

	public RealResult(T resultValue) {
		this.resultValue = resultValue;
	}

	@Override
	public T getResultValue() {
		return resultValue;
	}
}
