package com.github.aidan.java8.test.design.mediator;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * @author wang yi fei
 * @date 2019/5/27 14:14
 */
public class ColleagueCheckBox extends Checkbox implements ItemListener,Colleague {
	private Mediator mediator;

	public ColleagueCheckBox(String label, CheckboxGroup group, boolean state) throws HeadlessException {
		super(label, group, state);
	}

	@Override
	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}

	@Override
	public void setColleagueEnabled(boolean enabled) {
		setEnabled(enabled);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		mediator.colleagueChanged();
	}
}
