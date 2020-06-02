package com.github.aidan.java8.test.design.flyweight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author wang yi fei
 * @date 2019/6/12 13:36
 */
public class BigChar {

	private char charname;

	private String fontdata;

	public BigChar(char charname) {
		this.charname = charname;
		try {
			BufferedReader reader =new BufferedReader(new FileReader("big"+charname+".txt"));
			String line;
			StringBuffer buf = new StringBuffer();
			while ((line = reader.readLine()) != null){
				buf.append(line);
				buf.append("\n");
			}
			reader.close();
			this.fontdata = buf.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void print(){
		System.out.println(fontdata);
	}
}
