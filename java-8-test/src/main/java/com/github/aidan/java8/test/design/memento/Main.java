package com.github.aidan.java8.test.design.memento;

import com.github.aidan.java8.test.design.memento.game.Gamer;
import com.github.aidan.java8.test.design.memento.game.Memento;

/**
 * @author wang yi fei
 * @date 2019/5/30 13:45
 */
public class Main {

	public static void main(String[] args)  {
		Gamer gamer = new Gamer(100);
		Memento memento = gamer.createMemento();
		for (int i = 0;i < 100;i++){
			System.out.println("=== " + i);
			System.out.println(" 当前状态 :" + gamer);
			gamer.bet();
			System.out.println(" 所持金钱为 " + gamer.getMoney() + " 元。");
			// 决定如何处理 Memento
			if (gamer.getMoney() > memento.getMoney()){
				System.out.println(" 所持金钱增加了许多，因此保存游戏当前状态");
				memento = gamer.createMemento();
			}else if (gamer.getMoney() < memento.getMoney()/2){
				System.out.println("所持今天减少了许多，因此游戏恢复至以前的状态");
				gamer.restoreMemento(memento);
			}
			// 等待一段时间
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			System.out.println("");
		}
	}
}
