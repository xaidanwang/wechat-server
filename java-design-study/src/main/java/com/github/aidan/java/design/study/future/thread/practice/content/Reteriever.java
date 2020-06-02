package com.github.aidan.java.design.study.future.thread.practice.content;

/**
 * @author wang yi fei
 * @date 2020/5/19 15:11
 */
public class Reteriever {
	public static Content retrieve(String urlstr){
		return new SyncContentImpl(urlstr);
	}
}
