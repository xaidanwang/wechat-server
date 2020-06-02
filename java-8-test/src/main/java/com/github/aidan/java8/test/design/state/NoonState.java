package com.github.aidan.java8.test.design.state;

/**
 * @author wang yi fei
 * @date 2019/6/12 11:27
 */
public class NoonState implements State{
	private static NoonState ourInstance = new NoonState();

	public static NoonState getInstance() {
		return ourInstance;
	}

	private NoonState() {
	}

	@Override
	public void doClock(Context context, int hour) {
		if (hour < 9 || hour >= 17){
			context.changeState(NightState.getInstance());
		}else if (9 <= hour && hour < 12 || 13 <= hour && hour <17){
			context.changeState(DayState.getInstance());
		}
	}

	@Override
	public void doUse(Context context) {
		context.callSecurityCenter("使用金库（午餐）");
	}

	@Override
	public void doAlarm(Context context) {
		context.callSecurityCenter("按下警铃（午餐）");
	}

	@Override
	public void doPhone(Context context) {
		context.recordLog("午餐的通话录音");
	}


	@Override
	public String toString() {
		return "[ 午餐时间 ]";
	}
}
