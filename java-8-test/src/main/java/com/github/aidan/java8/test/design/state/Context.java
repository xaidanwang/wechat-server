package com.github.aidan.java8.test.design.state;

/**
 * @author wang yi fei
 * @date 2019/6/3 14:30
 */
public interface Context {
	//  设置时间
	public void setClock(int hour);

	//  改变状态
	public void changeState(State state);

	// 联系报警中心
	public void callSecurityCenter(String msg);

	//  在报警中心留下记录
	public void  recordLog(String msg);
}
