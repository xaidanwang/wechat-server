package com.github.aidan.java8.test.design.builder;

/**
 * @author wang yi fei
 * @date 2019/5/15 14:43
 */
public class Director {
	private Builder builder;

	public Director(Builder builder) {
		this.builder = builder;
	}

	public void construct(){
		builder.makeTitle("Greeting");
		builder.makeString("从早上至下午");
		builder.makeItems(new String[]{
				"早上好",
				"晚上好",
				"再见"
		});
		builder.close();
	}
}
