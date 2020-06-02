package com.github.aidan.java8.test.design.chain;

/**
 * @author wang yi fei
 * @date 2019/5/25 15:58
 */
public class SpecialSupport extends Support {
	private int number;

	public SpecialSupport(String name, int number) {
		super(name);
		this.number = number;
	}

	@Override
	protected boolean resolve(Trouble trouble) {
		if (trouble.getNumber() == number){
			return true;
		}
		return false;
	}
}
