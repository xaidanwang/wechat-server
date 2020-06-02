package com.github.aidan.java8.test.design.visitor;

import com.github.aidan.java8.test.design.visitor.practice.FileFindVisitor;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author wang yi fei
 * @date 2019/5/24 15:31
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("Making root entries");
		Director rootdir = new Director("root");
		Director binddir = new Director("bin");
		Director tmpdir = new Director("tmp");
		Director usrdir = new Director("usr");
		rootdir.add(binddir);
		rootdir.add(tmpdir);
		rootdir.add(usrdir);
		binddir.add(new File("vi",10000));
		binddir.add(new File("latex",20000));
		rootdir.accept(new ListVisitor());

		System.out.println("");
		System.out.println("Making user entries");
		Director yuki = new Director("yuki");
		Director hanako = new Director("hanako");
		Director tomura = new Director("tomura");
		usrdir.add(yuki);
		usrdir.add(hanako);
		usrdir.add(tomura);
		yuki.add(new File("diary.html",100));
		yuki.add(new File("Composite.java",200));
		yuki.add(new File("mwmo.tex",300));
		yuki.add(new File("game.doc",400));
		yuki.add(new File("junk.mail",500));

		tomura.add(new File("index.html",350));

		rootdir.accept(new ListVisitor());

		System.out.println("");
		System.out.println("HTML files are:");
		FileFindVisitor ffv = new FileFindVisitor("html");
		rootdir.accept(ffv);

		Iterator it = ffv.getFoundFiles();

		while (it.hasNext()){
			File file = (File)it.next();
			System.out.println(file);
		}
	}
}
