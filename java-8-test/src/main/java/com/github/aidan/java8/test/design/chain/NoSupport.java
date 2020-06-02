package com.github.aidan.java8.test.design.chain;

/**
 * @author wang yi fei
 * @date 2019/5/25 15:34
 */
public class NoSupport extends Support {

	public NoSupport(String name) {
		super(name);
	}

	@Override
	protected boolean resolve(Trouble trouble) {
		return false;
	}
}
