package com.github.aidan.java8.test.file;

import cn.hutool.core.io.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author wang yi fei
 * @date 2019/12/17 14:41
 */
public class Main {

	public static void main(String[] args) throws IOException {
		File txtFile = new File("D:\\tmp\\test.txt");
//		System.out.println(txtFile.length());
		System.out.println(txtFile.getAbsolutePath());
		BufferedReader fileReader1 = FileUtil.getReader(txtFile,"UTF-8");
		String str = null;
		while ((str = fileReader1.readLine()) != null) {
			System.out.println(str);
			String[] con = str.split("\",\"");
			System.out.println(con.length);
			for (int i =0;i< con.length; i++){
				System.out.println(con[i]);
				if (i == 0){
					System.out.println("==============");
					System.out.println(con[i].substring(1));
					System.out.println("==============");
				}
			}
		}
		long lines = Files.lines(Paths.get(txtFile.getPath())).count();
		System.out.println(lines);
	}
}
