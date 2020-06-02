package com.github.aidan.java.design.study.active;

/**
 * @author wang yi fei
 * @date 2020/5/28 16:43
 * 代理指定方法的类，包括真正执行方法的实例 servant，
 * 使用了 future 模式 scheduler
 */
public class Proxy implements ActiceObject {

	private final SchedulerThread scheduler;
	private final Servant servant;

	public Proxy(SchedulerThread scheduler, Servant servant) {
		this.scheduler = scheduler;
		this.servant = servant;
	}

	@Override
	public Result<String> makeString(int count, char fillchar) {
		FutureResult<String> future = new FutureResult();
		scheduler.invoke(new MakeStringRequest(servant,future,count,fillchar));
		return future;
	}

	@Override
	public void displayString(String string) {
		scheduler.invoke(new DispalyStringRequest(servant,string));
	}
}
