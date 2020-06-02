package com.github.aidan.java8.test.design.practice;

import com.github.aidan.java8.test.design.abstractfactory.factory.Page;

import java.util.Date;

/**
 * @author wang yi fei
 * @date 2019/5/31 17:40
 */
public class DateTerm {

	private Date startTime;

	private Date endTime;

	private boolean isDuplicate;

	public void compareTo(DateTerm dateTerm){
		// a = true 或者 b = true 时间重合
		boolean a = this.startTime.after(dateTerm.endTime);
		boolean b = this.endTime.before(dateTerm.startTime);
		isDuplicate = a || b;
	}

	public boolean isDuplicate() {
		return isDuplicate;
	}
}
