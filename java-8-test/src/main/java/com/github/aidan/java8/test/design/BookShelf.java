package com.github.aidan.java8.test.design;

/**
 * @author wang yi fei
 * @date 2019/5/8 20:00
 */
public class BookShelf implements Aggregate{
	private Book[] books;
	private int last = 0;
	public BookShelf(int maxSize) {
		this.books = new Book[maxSize];
	}
	public Book  getBookAt(int index){
		return books[index];
	}
	public void appendBook(Book book){
		this.books[last] = book;
		last++;
	}
	public int getLength(){
		return last;
	}
	@Override
	public Iterator iterator() {
		return new BookShelfIterator(this);
	}
}
