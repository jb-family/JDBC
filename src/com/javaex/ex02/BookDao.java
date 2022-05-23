package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	
	//필드
		
	//생성자
	
	//메소드 - gs
	
	//메소드 - 일반
	
	//책 등록 메소드
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
			pstmt.setString(3,pubDate);
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
	
	//책 수정 메소드
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
			query += " ,pub_date = ? ";
			query += "     ,author_id = ? ";
			query += " where book_id = ? ";
			
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(5, bookId);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, authorId);
			
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
	
	//책 삭제 메소드
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
			
			//book 
			
		
			String queryAll = "";
			queryAll += " select  bo.book_id ";
			queryAll += "         ,bo.title ";
			queryAll += "         ,bo.pubs ";
			queryAll += "         ,bo.pub_date ";
			queryAll += "         ,aut.author_name ";
			queryAll += " from    book bo, author aut ";
			queryAll += " where   bo.author_id = aut.author_id ";
			System.out.println(queryAll);	
			
			//바인딩
			//pstmt = conn.prepareStatement(query);
			pstmt = conn.prepareStatement(queryAll);
			
			//실행
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			
			
			while(rs.next()) {
				int bookId = rs.getInt(1);
				String title = rs.getString(2);
				String pubs = rs.getString(3);
				java.sql.Date pubDate = rs.getDate(4);
				String authorName = rs.getString(5);
				BookVo BookVo = new BookVo(bookId, title, pubs, pubDate, authorName);
				
				bookList.add(BookVo);
				
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
