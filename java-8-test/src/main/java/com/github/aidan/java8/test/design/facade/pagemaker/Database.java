package com.github.aidan.java8.test.design.facade.pagemaker;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author wang yi fei
 * @date 2019/5/25 17:57
 */
public class Database {

	Database(){}

	public static Properties getProperties(String dbname){ // 根据数据库名获取 Properties
		String filename = dbname +".txt";
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(filename));
		}catch (IOException e){
			System.out.println("Warning: "+filename + "is not found.");
		}
		return prop;
	}
}
