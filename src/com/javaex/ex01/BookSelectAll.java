package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelectAll {

	public static void main(String[] args) {

		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		try {
		// 1. JDBC 드라이버(Oracle) 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");	
			
		// 2. Connection 얻어오기
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(url, "webdb", "webdb");	
		
		
		// 3. //SQL문준비
		String query = "";
		query += " select  bo.book_id ";
		query += "         ,bo.title ";
		query += "         ,bo.pubs ";
		query += "         ,bo.pub_date ";
		query += "         ,bo.author_id ";
		query += "         ,aut.author_name ";
		query += "         ,aut.author_desc ";
		query += " from    book bo, author aut ";
		query += " where   bo.author_id = aut.author_id ";
		
		//바인딩
		pstmt = conn.prepareStatement(query);
		
		//실행
		rs = pstmt.executeQuery();
		
		// 4.결과처리
		while(rs.next()) {
			int bookId = rs.getInt(1);
			String title = rs.getString(2);
			String pubs = rs.getString(3);
			java.sql.Date pubDate = rs.getDate(4);
			int authorId = rs.getInt(5);
			String authorName = rs.getString(6);
			String authorDesc = rs.getString(7);
			
			
			System.out.println(bookId + ". " + title + ", " + pubs + ", " + pubDate + ", " + authorId + ", " + authorName + ", " + authorDesc);
		}
		
		
		
			
		} catch (ClassNotFoundException e) {
		System.out.println("error: 드라이버로딩실패-"+ e);
		} catch (SQLException e) {
		System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
		try {
		if (rs!= null) {
		rs.close();
		}
		if (pstmt!= null) {
		pstmt.close();
		}
		if (conn != null) {
		conn.close();
		}
		} catch (SQLException e) {
		System.out.println("error:" + e);
		}
		}
		
		
	}

}
