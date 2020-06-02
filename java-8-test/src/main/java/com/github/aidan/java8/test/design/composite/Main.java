package com.github.aidan.java8.test.design.composite;

import com.github.aidan.java8.test.design.builder.Director;

/**
 * @author wang yi fei
 * @date 2019/5/22 10:42
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("Making root entries");
		Directory rootdir = new Directory("root");
		Directory binddir = new Directory("bin");
		Directory tmpdir = new Directory("tmp");
		Directory usrdir = new Directory("usr");
		rootdir.add(binddir);
		rootdir.add(tmpdir);
		rootdir.add(usrdir);
		binddir.add(new File("vi",10000));
		binddir.add(new File("latex",20000));
		rootdir.printList();

		System.out.println("");
		System.out.println("Making user entries");
		Directory yuki = new Directory("yuki");
		Directory hanako = new Directory("hanako");
		Directory tomura = new Directory("tomura");
		usrdir.add(yuki);
		usrdir.add(hanako);
		usrdir.add(tomura);
		yuki.add(new File("diary.thml",100));
		yuki.add(new File("Composite.java",200));
		yuki.add(new File("mwmo.tex",300));
		yuki.add(new File("game.doc",400));
		yuki.add(new File("junk.mail",500));
		rootdir.printList();
	}
}
