package com.github.aidan.java8.test.design.singleton.practice;

/**
 * @author wang yi fei
 * @date 2019/5/13 15:39
 */
public class Triple {
	private int id;
	private static Triple[] triple = new Triple[]{
			new Triple(0),
			new Triple(1),
			new Triple(2),
	};

	private Triple(int id) {
		this.id = id;
	}

	public static Triple getInstance(int id){
		return  triple[id];
	}


}
