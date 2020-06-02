package com.github.aidan.java8.test.design.chain;

/**
 * @author wang yi fei
 * @date 2019/5/25 15:56
 */
public class OddSuport extends Support {

	public OddSuport(String name) {
		super(name);
	}

	@Override
	protected boolean resolve(Trouble trouble) {

		if (trouble.getNumber() % 2 ==1){
			return true;
		}
		return false;
	}
}
