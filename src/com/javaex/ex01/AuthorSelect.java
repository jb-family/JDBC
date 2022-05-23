package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorSelect {

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
		
		
		
		// 3. SQL문준비/ 바인딩/ 실행 
		//SQL문준비
		String query = "";
		query += " select  author_id ";
		query += " ,author_name ";
		query += " ,author_desc ";
		query += " from    author ";
		//바인딩
		//select는 ? 가 없다.
		pstmt = conn.prepareStatement(query);
		  
		
		
		
		//실행
		//select는 ResultSet, insert, update, delete는 int count
		rs = pstmt.executeQuery();	
			
		// 4.결과처리
		while(rs.next()) {	// rs.next()가 내려갈게 있다면 true 없다면 false가되고 while문 밖으로 나옴. break 없어도됨.
		
			//int authorId = rs.getInt("author_id");
			//String authorName = rs.getString("author_name");
			//String authorDesc = rs.getString("author_desc");
			
			int authorId = rs.getInt(1);
			String authorName = rs.getString(2);
			String authorDesc = rs.getString(3);
			
			System.out.println(authorId + ", " + authorName + ", " + authorDesc);
			
		}
		
		
		//rs.next(); 한줄 밑으로 내려감
		

		
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
