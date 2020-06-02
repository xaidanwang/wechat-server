package com.github.aidan.java8.test.design.command.drawer;

import com.github.aidan.java8.test.design.command.Command;

import java.awt.*;

/**
 * @author wang yi fei
 * @date 2019/6/14 10:17
 */
public class ColorCommand implements Command {
	//  绘制对象
	protected Drawable drawable;
	private Color color;


	public ColorCommand(Drawable drawable, Color color) {
		this.drawable = drawable;
		this.color = color;
	}

	@Override
	public void execute() {
		drawable.setClor(color);
	}
}
