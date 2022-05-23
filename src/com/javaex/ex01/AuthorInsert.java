package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorInsert {

   public static void main(String[] args) {
      
      // 0. import java.sql.*;
      Connection conn = null;
      PreparedStatement pstmt = null;
      //ResultSet rs = null;
      try {
      // 1. JDBC 드라이버 (Oracle) 로딩
      Class.forName("oracle.jdbc.driver.OracleDriver");
      
      // 2. Connection 얻어오기
      String url = "jdbc:oracle:thin:@localhost:1521:xe";
      conn = DriverManager.getConnection(url, "webdb", "webdb");	// 주소, 아이디, 패스워드

      
    
      
      // 3. SQL문 준비 / 바인딩 / 실행
      // SQL문 준비
      //str = str + 문자열
      /*
      String query = "";
      query += " insert into author ";
      query += " values (seq_author_id.nextval, '이문열', '경북영양') ";
      */
      // oracle에서 인식하려면 문자열을 한번 더 가공해서 읽을 수 있도록 해줘야한다.
      // 입력해야하는 곳은 ?로 해주면 된다. (그냥 문법임) 
      String query = "";
      query += " insert into author ";
      query += " values (seq_author_id.nextval, ?, ?) ";
      
      

      //바인딩 - values안에 있는 문자열 ?를 query로 만들어주는 일을 함
      pstmt = conn.prepareStatement(query);	//문자열을 query로 만들기.
      //1번째매개변수는 순서지정 1은 첫번째 ?	2번째 매개변수는 입력하고싶은값

      pstmt.setString(1, "김영하");		  
      pstmt.setString(2, "알쓸신잡");	

      
      //실행
      int count = pstmt.executeUpdate();	//query문 실행 --> 성공개수 리턴
      
      
      // 4.결과처리
      System.out.println(count + "건이 등록 되었습니다.");
      
      
      } catch (ClassNotFoundException e) {
      System.out.println("error: 드라이버 로딩 실패 - " + e);
      } catch (SQLException e) {
      System.out.println("error:" + e);
      } finally {
      // 5. 자원정리
      try {
      /*
      if (rs != null) {
      rs.close();
      } 
      */
      if (pstmt != null) {
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