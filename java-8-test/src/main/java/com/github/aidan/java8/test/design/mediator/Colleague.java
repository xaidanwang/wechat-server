package com.github.aidan.java8.test.design.mediator;

/**
 * @author wang yi fei
 * @date 2019/5/27 14:02
 */
public interface Colleague {
	public abstract void setMediator(Mediator mediator);
	public abstract void setColleagueEnabled(boolean enabled);
}
