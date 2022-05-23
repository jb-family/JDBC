package com.javaex.ex03;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class BookApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
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
		
		System.out.println("======전체도서======");
		for(int i = 0; i < bookList.size(); i++) {
			int bookId = bookList.get(i).getBookId();
			String title = bookList.get(i).getTitle();
			String pubs = bookList.get(i).getPubs();
			Date pubDate = bookList.get(i).getPubDate();
			String authorName = bookList.get(i).getAuthorName();

			System.out.println(bookId + ", " + title + ", " + pubs + ", " + pubDate + ", " + authorName);
			}	
		System.out.println("======검 색======");
		String search = sc.next();
		for(int i = 0; i < bookList.size(); i++) {
			int bookId = bookList.get(i).getBookId();
			String title = bookList.get(i).getTitle();
			String pubs = bookList.get(i).getPubs();
			Date pubDate = bookList.get(i).getPubDate();
			String authorName = bookList.get(i).getAuthorName();
			
			
			if(title.contains(search) || pubs.contains(search) || authorName.contains(search)) {
				System.out.println(bookId + ", " + title + ", " + pubs + ", " + pubDate + ", " + authorName);
			}	
		}
		System.out.println("======검색결과======");
		
		sc.close();
	}

}
