package com.github.aidan.java.design.study.active;

/**
 * @author wang yi fei
 * @date 2020/5/28 16:01
 */
public class ActiceObjectFactory {

	public static ActiceObject createActiceObject(){
		ActivarionQuene quene = new  ActivarionQuene();
		SchedulerThread scheduler = new SchedulerThread(quene);
		Servant servant = new Servant();
		Proxy proxy = new Proxy(scheduler,servant);
		scheduler.start();
		return proxy;
	}
}
