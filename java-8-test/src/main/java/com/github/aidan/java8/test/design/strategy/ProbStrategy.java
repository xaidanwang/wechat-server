package com.github.aidan.java8.test.design.strategy;

import java.util.Random;

/**
 * @author wang yi fei
 * @date 2019/5/20 14:26
 */
public class ProbStrategy implements Strategy {
	private Random random;
	private int prevHandValue = 0;
	private int currentHandValue = 0;
	private int[][] history = {
			{1,1,1,},
			{1,1,1,},
			{1,1,1,},
	};

	public ProbStrategy(int seed) {
		this.random = new Random();
	}
	@Override
	public Hand nextHand() {
		int bet = random.nextInt(getSum(currentHandValue));
		int handvalue = 0;
		if (bet < history[currentHandValue][0]){
			handvalue = 0;
		}else if (bet < history[currentHandValue][0] + history[currentHandValue][1]){
			handvalue =1;
		}else {
			handvalue = 2;
		}
		prevHandValue = currentHandValue;
		currentHandValue = handvalue;
		return Hand.getHand(handvalue);
	}

	@Override
	public void study(boolean win) {
		if (win){
			history[prevHandValue][currentHandValue]++;
		}else {
			history[prevHandValue][(currentHandValue+1) % 3]++;
			history[prevHandValue][(currentHandValue+2) % 3]++;
		}
	}
	private int getSum(int hv){
		int sum = 0;
		for (int i=1;i<3;i++){
			sum += history[hv][i];
		}
		return sum;
	}

	public static void main(String[] args) {
	}
}
