package com.github.aidan.java8.test.design;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author wang yi fei
 * @date 2019/5/8 19:56
 */
public class Book {
	private String name;

	public Book(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public static void main(String[] args) {
		BookShelf bookShelf = new BookShelf(4);
		bookShelf.appendBook(new Book("Around the World in 80 Days"));
		bookShelf.appendBook(new Book("Bible"));
		bookShelf.appendBook(new Book("Cinderella"));
		bookShelf.appendBook(new Book("Daddy-Long-legs"));
		Iterator it = bookShelf.iterator();
		while (it.hasNext()){
			Book book = (Book) it.next();
			System.out.println(book.getName());
		}

		List<Integer> integers = new ArrayList<>();

		for (int i =1;i<20;i++){
			integers.add(i);
		}
		List<Integer> newin = integers.stream().filter(integer -> integer>10).collect(Collectors.toList());

		System.out.println(integers);
		System.out.println(newin);
	}
}
