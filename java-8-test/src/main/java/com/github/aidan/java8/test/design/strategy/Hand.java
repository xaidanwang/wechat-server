package com.github.aidan.java8.test.design.strategy;

import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * @author wang yi fei
 * @date 2019/5/20 13:51
 */
public class Hand {
	public static final int HANDVALUE_GUU = 0;
	public static final int HANDVALUE_CHO = 1;
	public static final int HANDVALUE_PAA = 2;
	public static final Hand[] hand = {
	new Hand(HANDVALUE_GUU),
	new Hand(HANDVALUE_CHO),
	new Hand(HANDVALUE_PAA),
	};
	private int handvalue;

	public Hand(int handvalue) {
		this.handvalue = handvalue;
	}
	private static final String[] name = {"石头","剪刀","布"};
	public static Hand getHand(int handvalue){
		return hand[handvalue];
	}
	public boolean isStrongerThan(Hand h){
		return fight(h) == 1;
	}

	public boolean isWeakerThan(Hand h){
		return fight(h) == -1;
	}

	private int fight(Hand h){
		if (this == h){
			return 0;
		}else if ((this.handvalue + 1)%3 == h.handvalue){
			return 1;
		}else {
			return -1;
		}
	}
	public String toString(){
		return name[handvalue];
	}
}
