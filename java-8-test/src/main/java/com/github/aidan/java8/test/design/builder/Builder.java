package com.github.aidan.java8.test.design.builder;

/**
 * @author wang yi fei
 * @date 2019/5/15 14:27
 */
public abstract class Builder {

	protected boolean initialized = false;
	public  void makeTitle(String title){
		if (!initialized){
			buildTitle(title);
			initialized = true;
		}
	}
	public  void makeString(String str){
		if (initialized){
			buildString(str);
		}
	}
	public  void makeItems(String[] items){
		if (initialized){
			buildItems(items);
		}
	}
	public  void close(){
		if (initialized){
			buildDone();
		}
	}

	protected abstract void buildTitle(String title);
	protected abstract void buildString(String str);
	protected abstract void buildItems(String[] items);
	protected abstract void buildDone();
}
