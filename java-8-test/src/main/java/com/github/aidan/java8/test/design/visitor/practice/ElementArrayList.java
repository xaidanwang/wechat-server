package com.github.aidan.java8.test.design.visitor.practice;


import com.github.aidan.java8.test.design.visitor.Element;
import com.github.aidan.java8.test.design.visitor.Visitor;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author wang yi fei
 * @date 2019/5/25 14:42
 */
public class ElementArrayList extends ArrayList implements Element{

	@Override
	public void accept(Visitor visitor) {
		Iterator it = iterator();
		while (it.hasNext()){
			Element element = (Element)it.next();
			element.accept(visitor);
		}
	}
}
