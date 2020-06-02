package com.github.aidan.java8.test.design.chain;

/**
 * @author wang yi fei
 * @date 2019/5/25 15:35
 */
public class LimitSupport extends Support {
	private int limit;

	public LimitSupport(String name, int limit) {
		super(name);
		this.limit = limit;
	}

	@Override
	protected boolean resolve(Trouble trouble) {
		if (trouble.getNumber() < limit){
			return true;
		}
		return false;
	}
}
