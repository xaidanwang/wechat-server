package com.github.aidan.java8.test.producer.consumer;

/**
 * @author wang yi fei
 * @date 2019/12/13 14:19
 */
public class Main {


	public static void main(String[] args) {
		Table table = new Table(2);
		new MakerThread( "MakerThread-1",table,31415).start();
		new MakerThread( "MakerThread-2",table,92653).start();
		new MakerThread( "MakerThread-3",table,58979).start();
		new EaterThread( "EaterThread-1",table,32384).start();
		new EaterThread( "EaterThread-2",table,62643).start();
		new EaterThread( "EaterThread-3",table,38327).start();
	}
}
