package com.github.aidan.java8.test.design.state;

/**
 * @author wang yi fei
 * @date 2019/6/3 14:29
 */
public interface State {
	//  设置时间
	public void doClock(Context context,int hour);
	//  使用金库
	public void doUse(Context context);
	//  按下警铃
	public void doAlarm(Context context);
	//  正常通话
	public void doPhone(Context context);
}
