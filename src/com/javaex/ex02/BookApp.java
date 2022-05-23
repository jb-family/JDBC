package com.javaex.ex02;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {

		
		BookDao bookDao = new BookDao();
		
		/*
		bookDao.bookInsert("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
		bookDao.bookInsert("삼국지", "민음사", "2002-03-01", 1);
		bookDao.bookInsert("토지", "마로니에북스", "2012-08-15", 2);
		bookDao.bookInsert("자바프로그래밍 입문", "위키북스", "2015-04-01", 3);
		bookDao.bookInsert("패션왕", "중앙북스(books)", "2012-02-22", 4);
		bookDao.bookInsert("순정만화", "재미주의", "2011-08-03", 5);
		bookDao.bookInsert("오직두사람", "문학동네", "2017-05-04", 6);
		bookDao.bookInsert("26년", "재미주의", "2012-02-04", 5);
		
		
		bookDao.bookInsert("28년", "재미주의", "2011-11-11", 6);
		bookDao.bookUpdate(9, "278년", "노잼주의", "2011-12-31", 6);
		bookDao.bookDelete(9);
		*/
		
		
		List<BookVo> bookList = bookDao.bookSelect();
		
		
		for(int i = 0; i < bookList.size(); i++) {
			System.out.println(bookList.get(i).getBookId() + ", " + bookList.get(i).getTitle() + ", " + bookList.get(i).getPubs() + ", " + bookList.get(i).getPubDate() + ", " + bookList.get(i).getAuthorName());
			
		}
		
		
	}

}
