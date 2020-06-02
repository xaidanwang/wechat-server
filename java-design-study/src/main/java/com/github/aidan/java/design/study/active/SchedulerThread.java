package com.github.aidan.java.design.study.active;

/**
 * @author wang yi fei
 * @date 2020/5/28 16:47
 */
public class SchedulerThread extends Thread {
	private final ActivarionQuene quene;

	public SchedulerThread(ActivarionQuene quene) {
		this.quene = quene;
	}

	public void invoke(MethodRequest request){
		quene.putRequest(request);
	}

	@Override
	public void run() {
		while (true){
			MethodRequest request = quene.takeRequest();
			request.execute();
		}
	}
}
