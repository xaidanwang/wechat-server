package com.github.aidan.java8.test.design.adapter.practice;

import java.io.*;
import java.util.Properties;

/**
 * @author wang yi fei
 * @date 2019/5/11 16:38
 */
public class FileProperties extends Properties implements FileIO {

	@Override
	public void readFromFile(String fileName) throws IOException {
		Reader reader = new FileReader(fileName);
		load(reader);
	}

	@Override
	public void writeToFile(String fileName) throws IOException {
		Writer writer = new FileWriter("C:\\Users\\Administrator\\Desktop\\yun\\"+fileName);
		store(writer,"written by FileProperties");
	}

	@Override
	public void setValue(String key, String value) {
		setProperty(key,value);
	}

	@Override
	public String getValue(String key) {
		return getProperty(key);
	}

	public static void main(String[] args) {
		FileIO f = new FileProperties();
		try {
			f.readFromFile("C:\\Users\\Administrator\\Desktop\\yun\\file.txt");
			System.out.println(f.getValue("year"));
			f.setValue("year","2004");
			f.setValue("month","4");
			f.setValue("day","21");
			f.writeToFile("newFile.txt");
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
