package com.github.aidan.java8.test.file;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * @author wang yi fei
 * @date 2019/12/22 11:25
 */
@Slf4j
public class FileTest {


	public static void main(String[] args) {

		File dir = new File("/tmp/data/zip/111111111");
		if (!dir.exists()) {
			log.info("创建目录{}",dir.getAbsolutePath());
			dir.mkdirs();// 可创建多级目录，而mkdir()只能创建一级目录
		}
	}
}
