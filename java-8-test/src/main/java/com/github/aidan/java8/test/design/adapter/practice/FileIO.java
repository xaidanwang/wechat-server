package com.github.aidan.java8.test.design.adapter.practice;

import java.io.IOException;

/**
 * @author wang yi fei
 * @date 2019/5/11 16:23
 */
public interface FileIO {
	public void readFromFile(String fileName) throws IOException;
	public void writeToFile(String fileName) throws IOException;
	public void setValue(String key,String value);
	public String getValue(String key);
}
