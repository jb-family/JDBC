package com.javaex.ex03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookDao {
	//필드
	
	//생성자
	
	//메소드 - gs
	
	//메소드 - 일반
	public int bookInsert(String title, String pubs, String pubDate, int authorId) {
	
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		int count = -1;
			try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
				
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");	
				
			// 3. SQL문준비/ 바인딩/ 실행
			//SQL문준비
			String query = "";
			query += " insert into book ";
			query += " values (seq_book_id.nextval, ?, ?, ?, ?) ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, authorId);
			
			//실행
			count = pstmt.executeUpdate();
			
			
			// 4.결과처리
			System.out.println(count + "건이 추가되었습니다.");	
				
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
		return count;
	}
	
	public int bookUpdate(int bookId, String title, String pubs, String pubDate, int authorId) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		int count = -1;
			
			try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");	
				
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");	
				
			// 3. SQL문준비/ 바인딩/ 실행
			//SQL문준비
			String query = "";
			query += " update book ";
			query += " set title = ? ";
			query += "     ,pubs = ? ";
			query += "     ,pub_date = ? ";
			query += "     ,author_id = ? ";
			query += " where book_id = ? ";
			
			
			//바인딩 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, authorId);
			pstmt.setInt(5, bookId);
			
			//실행
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println(count + "건이 수정되었습니다.");
				
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
			return count;
	}
	
	
	
	public int bookDelete(int bookId) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		int count = -1;
			try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
				
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");	
				
			// 3. SQL문준비/ 바인딩/ 실행
			//SQL문준비
			String query = "";
			query += " delete from book ";
			query += " where book_id = ? ";

			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);
			
			//실행
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println(count + "건이 삭제되었습니다.");
			
				
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
			return count;
	}
	
	
	public List<BookVo> bookSelect() {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		List<BookVo> bookList = new ArrayList<BookVo>();
		
			try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");	
				
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");	
				
			// 3. SQL문준비/ 바인딩/ 실행
			//SQL문준비
			String query = "";
			query += " select  bo.book_id ";
			query += "         ,bo.title ";
			query += "         ,bo.pubs ";
			query += "         ,bo.pub_date ";
			query += "         ,aut.author_name ";
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
				String authorName = rs.getString(5);
				
				BookVo bookVo = new BookVo(bookId, title, pubs, pubDate, authorName);
				
				bookList.add(bookVo);
				
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
			
			return bookList;
			
	}
	

	
	
}
