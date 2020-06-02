package com.github.aidan.java8.test.design.abstractfactory.factory;

import java.util.ArrayList;

/**
 * @author wang yi fei
 * @date 2019/5/16 20:48
 */
public abstract class Tray extends Item{
	protected ArrayList tray = new ArrayList();

	public Tray(String caption) {
		super(caption);
	}

	public void add(Item item){
		tray.add(item);
	}
}
