package com.github.aidan.java8.test.design.mediator;

import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

/**
 * @author wang yi fei
 * @date 2019/5/27 14:10
 */
public class ColleagueTextField extends TextField implements TextListener,Colleague {

	private Mediator mediator;

	public ColleagueTextField(String text, int columns) throws HeadlessException {
		super(text, columns);
	}

	@Override
	public void setMediator(Mediator mediator) {
	   this.mediator = mediator;
	}

	@Override
	public void setColleagueEnabled(boolean enabled) {
		setEnabled(enabled);
		setBackground(enabled ? Color.white: Color.lightGray);
	}

	@Override
	public void textValueChanged(TextEvent e) {
		 mediator.colleagueChanged();
	}
}
