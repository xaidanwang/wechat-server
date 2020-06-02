package com.github.aidan.java8.test.design.mediator;

import java.awt.*;

/**
 * @author wang yi fei
 * @date 2019/5/27 14:05
 */
public class ColleagueButton extends Button implements Colleague{

	private Mediator mediator;

	public ColleagueButton(String caption) throws HeadlessException {
		super(caption);
	}

	@Override
	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}

	@Override
	public void setColleagueEnabled(boolean enabled) {
		setEnabled(enabled);
	}
}
