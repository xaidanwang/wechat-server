package com.github.aidan.java8.test.design.practice;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author wang yi fei
 * @date 2019/5/31 17:38
 */
public class PreiodDate {

	private Date startDate;

	private Date endDate;

	private List<DateTerm> dateTerms;


	private void aa(){
		Iterator it = dateTerms.iterator();
		while (it.hasNext()){
			DateTerm dateTerm = (DateTerm)it.next();

		}
	}

}
