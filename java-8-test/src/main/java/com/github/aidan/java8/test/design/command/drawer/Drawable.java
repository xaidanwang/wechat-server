package com.github.aidan.java8.test.design.command.drawer;

import java.awt.*;

/**
 * @author wang yi fei
 * @date 2019/6/13 14:58
 */
public interface Drawable {
	public abstract void draw(int x,int y);
	public abstract void setClor(Color clor);
}
