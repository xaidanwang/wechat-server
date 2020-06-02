package com.github.aidan.java8.test.design.state;

/**
 * @author wang yi fei
 * @date 2019/6/5 14:55
 */
public class NightState implements State{
	private static NightState ourInstance = new NightState();

	public static NightState getInstance() {
		return ourInstance;
	}

	private NightState() {
	}

	@Override
	public void doClock(Context context, int hour) {
		if (hour >= 9 && hour <17){
			context.changeState(DayState.getInstance());
		}else if ( 12 <= hour && hour < 13){
			context.changeState(NoonState.getInstance());
		}
	}

	@Override
	public void doUse(Context context) {
		context.callSecurityCenter("紧急：晚上使用金库!");
	}

	@Override
	public void doAlarm(Context context) {
		context.callSecurityCenter("按下警铃（晚上）");
	}

	@Override
	public void doPhone(Context context) {
		context.recordLog("晚上的通话录音");
	}

	@Override
	public String toString() {
		return "[ 晚上 ]";
	}
}
