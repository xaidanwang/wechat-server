package com.github.aidan.java8.test.design.command.drawer;

import com.github.aidan.java8.test.design.command.MacroCommand;

import java.awt.*;

/**
 * @author wang yi fei
 * @date 2019/6/13 15:26
 */
public class DrawCanvas extends Canvas implements Drawable {

	private Color color = Color.red;
	private int radius = 6;
	private MacroCommand history;

	public DrawCanvas(int width,int height,MacroCommand history) {
		setSize(width,height);
		setBackground(Color.white);
		this.history = history;
	}

	@Override
	public void draw(int x, int y) {
		Graphics g = getGraphics();
		g.setColor(color);
		g.fillOval(x - radius,y - radius,radius * 2,radius * 2);
	}

	//  重新绘制
	public void paint(Graphics g){
		history.execute();
	}

	@Override
	public void setClor(Color clor) {
		this.color = clor;
	}
}
