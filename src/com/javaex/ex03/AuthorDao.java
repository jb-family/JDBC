package com.javaex.ex03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	//필드

	//생성자
	
	//메소드 - gs
	
	//메소드 - 일반
	
	public int authorInsert(String authorName, String authorDesc) {
		int count = -1;
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
				
			// 3. SQL문준비/ 바인딩/ 실행
			//SQL문준비
			String query = "";
			query += " insert into author ";	
			query += " values (seq_author_id.nextval, ?, ?) ";	
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1,authorName);
			pstmt.setString(2,authorDesc);
			
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
	
	public int authorUpdate(String authorName, String authorDesc, int authorId) {
		
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
			query += " update author ";
			query += " set author_name = ? ";
			query += "     ,author_desc = ? ";
			query += " where author_id = ? ";
			//바인딩
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, authorName);
			pstmt.setString(2, authorDesc);
			pstmt.setInt(3, authorId);
			
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
	
	public int authorDelete(int authorId) {
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
			query += " delete from author ";
			query += " where author_id = ? ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);
			
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
	
	
	public List<AuthorVo> authorSelect() {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
			try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");	
				
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
				
			// 3. SQL문준비/ 바인딩/ 실행
			//SQL문준비
			String query = "";
			query += " select  author_id ";
			query += "         ,author_name ";
			query += "         ,author_desc ";
			query += " from    author ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			
			
			//실행
			rs = pstmt.executeQuery();
				
			// 4.결과처리
			while(rs.next()) {
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				AuthorVo authorVo = new AuthorVo(authorId, authorName, authorDesc);
				
				authorList.add(authorVo);
				
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
			return authorList;
	}
	
	
	
}
