package com.github.aidan.java8.test.design.state;

/**
 * @author wang yi fei
 * @date 2019/6/3 14:44
 */
public class DayState implements State{
	private static DayState ourInstance = new DayState();

	public static DayState getInstance() {
		return ourInstance;
	}

	private DayState() {
	}

	@Override
	public void doClock(Context context, int hour) {
		if (hour < 9 || hour >= 17){
			context.changeState(NightState.getInstance());
		}else if ( 12 <= hour && hour < 13){
			context.changeState(NoonState.getInstance());
		}
	}

	@Override
	public void doUse(Context context) {
		context.recordLog("使用金库（白天）");
	}

	@Override
	public void doAlarm(Context context) {
		context.callSecurityCenter("按下警铃（白天）");
	}

	@Override
	public void doPhone(Context context) {
		context.callSecurityCenter("正常通话（白天）");
	}

	@Override
	public String toString() {
		return "[ 白天 ]";
	}
}
