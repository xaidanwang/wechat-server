package com.github.aidan.java8.test.design.chain;

/**
 * @author wang yi fei
 * @date 2019/5/25 15:59
 */
public class Main {
	public static void main(String[] args) {
		Support alice = new NoSupport("alice");
		Support bob = new LimitSupport("bob",100);
		Support charlie = new SpecialSupport("Charlie",429);
		Support diana = new LimitSupport("Diana",200);
		Support elmo = new OddSuport("Elmo");
		Support fred = new LimitSupport("Fred",300);
		// 形成职责链
		alice.setNext(bob).setNext(charlie).setNext(diana).setNext(elmo).setNext(fred);

		// 知道各种问题
		for (int i =0 ;i< 500;i++){
			alice.support(new Trouble(i));
		}
	}
}
