package com.javaex.ex03;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {

	AuthorDao authorDao = new AuthorDao();
	/*
	authorDao.authorInsert("이문열", "경북 영양");
	authorDao.authorInsert("박경리", "경상남도 통영");
	authorDao.authorInsert("이고잉", "17대 국회의원");
	authorDao.authorInsert("기안84", "기안동에서 산 84년생");
	authorDao.authorInsert("강풀", "온라인 만화가 1세대");
	authorDao.authorInsert("김영하", "알쓸신잡");
	authorDao.authorInsert("김난도", "난세의영웅");
	*/
	/*
	authorDao.authorUpdate("이난도", "오줌싸개", 7);
	authorDao.authorDelete(7);
	*/
	
	List<AuthorVo> authorList = authorDao.authorSelect();
	
	for(int i = 0; i < authorList.size(); i++) {
		int authorId = authorList.get(i).getAuthorId(); 
		String authorName = authorList.get(i).getAuthorName(); 
		String authorDesc = authorList.get(i).getAuthorDesc();
		
		System.out.println(authorId + ", " + authorName + ", " + authorDesc);
	}
	
	}

}
