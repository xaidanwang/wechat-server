package com.github.aidan.java8.test.design.command.drawer;

import com.github.aidan.java8.test.design.command.Command;
import com.github.aidan.java8.test.design.command.drawer.Drawable;

import java.awt.*;

/**
 * @author wang yi fei
 * @date 2019/6/13 14:57
 */
public class DrawCommand implements Command {
	//  绘制对象
	protected Drawable drawable;

	//  绘制位置
	private Point position;

	public DrawCommand(Drawable drawable, Point position) {
		this.drawable = drawable;
		this.position = position;
	}

	@Override
	public void execute() {
		drawable.draw(position.x,position.y);
	}
}
