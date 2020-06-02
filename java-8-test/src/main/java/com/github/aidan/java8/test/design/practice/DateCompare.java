package com.github.aidan.java8.test.design.practice;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * @author wang yi fei
 * @date 2019/6/1 15:48
 */
public abstract class DateCompare {

	private Date startTime;
	private Date endTime;

	protected boolean isDuplicate;

	private ArrayList list = new ArrayList();

	protected abstract boolean compareTo(DateCompare dateCompare);

	public boolean isDuplicate() {
		return isDuplicate;
	}

	public void compareTo(){
		isDuplicate = compareTo(this);
	}

	public void aass(){
		boolean flag = false;
		stop:
		for (int i = 0;i< list.size();i++){
			DateCompare dateCompare = (DateCompare)list.get(i);
			for (int j = i+1;j < list.size();j++){
				flag = dateCompare.compareTo((DateCompare)list.get(j));
				if (flag == true){
					break stop;
				}
			}
		}
	}
}
