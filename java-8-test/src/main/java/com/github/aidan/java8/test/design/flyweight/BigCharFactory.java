package com.github.aidan.java8.test.design.flyweight;

import java.util.HashMap;

/**
 * @author wang yi fei
 * @date 2019/6/12 13:54
 */
public class BigCharFactory {
	private static BigCharFactory ourInstance = new BigCharFactory();

	public static BigCharFactory getInstance() {
		return ourInstance;
	}

	private BigCharFactory() {
	}
	//  管理已经生成的 BigChar 的实例
	private HashMap pool = new HashMap();

	// 生成共享 BigChar 类的实例
	public synchronized  BigChar getBigChar(char charname){
		BigChar bc = (BigChar) pool.get(""+charname);
		if (bc == null){
			bc = new BigChar(charname);  //  生成 BigChar 的实例
			pool.put(""+charname,bc);
		}
		return bc;
	}
}
