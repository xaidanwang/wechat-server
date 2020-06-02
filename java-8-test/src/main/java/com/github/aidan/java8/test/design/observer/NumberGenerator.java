package com.github.aidan.java8.test.design.observer;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author wang yi fei
 * @date 2019/5/28 19:15
 */
public abstract class NumberGenerator {
	private ArrayList<Observer> observers = new ArrayList(); //  保存Observer 们
	public void addObserver(Observer observer){
		observers.add(observer);
	}
	public void deleteObserver(Observer observer){
		observers.remove(observer);
	}
	public void notifyObservers(){
		Iterator it = observers.iterator();
		while (it.hasNext()){
			Observer o = (Observer)it.next();
			o.update(this);
		}
	}
	public abstract int getNumber();  // 获取数值
	public abstract void execute();  // 生成数值

}
