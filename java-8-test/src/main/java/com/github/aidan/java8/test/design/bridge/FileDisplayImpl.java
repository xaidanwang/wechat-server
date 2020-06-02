package com.github.aidan.java8.test.design.bridge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author wang yi fei
 * @date 2019/5/18 16:48
 */
public class FileDisplayImpl extends DisplayImpl {

	private String filename;
	private BufferedReader bufferedReader;
	private final int MAX_READAHEAD_LIMIT = 4096;

	public FileDisplayImpl(String filename) {
		this.filename = filename;
	}

	@Override
	public void rawOpen() {
		try {
			bufferedReader = new BufferedReader(new FileReader(filename));
		}catch (IOException e){
			e.printStackTrace();
		}
		System.out.println("-=-=-=-=-=-" +filename+"-=-=-=-=-=-=-=-=-");
	}

	@Override
	public void rawPrint() {
		try {
			String line;
			bufferedReader.reset();
			while ((line = bufferedReader.readLine()) != null){
				System.out.println("> "+line);
			}
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void rawClose() {
		System.out.println("-=-=-=-=-=-=-=-=-=-");
		try {
			bufferedReader.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
