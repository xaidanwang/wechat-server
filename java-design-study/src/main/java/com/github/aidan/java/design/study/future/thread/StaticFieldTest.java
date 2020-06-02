package com.github.aidan.java.design.study.future.thread;


import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author wang yi fei
 * @date 2020/5/19 10:33
 */
public class StaticFieldTest {
	private static final sun.misc.Unsafe UNSAFE;
	private volatile Thread runner;
	private static final long runnerOffset;
	static {
		try {
			Field f = Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);
			UNSAFE = (Unsafe) f.get(null);
			Class<?> k = StaticFieldTest.class;
			runnerOffset = UNSAFE.objectFieldOffset
					(k.getDeclaredField("runner"));
			System.out.println(runnerOffset);
		} catch (Exception e) {
			throw new Error(e);
		}
	}
}
