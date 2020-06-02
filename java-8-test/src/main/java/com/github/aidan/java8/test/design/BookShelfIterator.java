package com.github.aidan.java8.test.design;

/**
 * @author wang yi fei
 * @date 2019/5/8 20:09
 */
public class BookShelfIterator implements Iterator {

	private BookShelf bookShelf;
	private int index;
	public BookShelfIterator(BookShelf bookShelf) {
		this.bookShelf = bookShelf;
		this.index = 0;
	}
	@Override
	public boolean hasNext() {
		if (index < bookShelf.getLength()){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Object next() {
		Book book = bookShelf.getBookAt(index);
		index++;
		return book;
	}
}
