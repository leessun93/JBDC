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
		
		//insert 문

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접쏙 썽공");
		  
			
			
			// 3. SQL문 준비 / 바인딩 / 실행
		    String query = ""; //문자열 만들기 --> ?주의
		    
		    //query  += 는 query + "문자열";
		    query += " insert into author ";    //쿼리문에서 글자가 붙는걸 막기위해 그냥 맨 앞 뒤 다 띄어써버림
			query += " values(seq_author_id.nextval, ?, ?) "; //내용이 계속 바뀌는 데이터부분은 ?로 표시함
			System.out.println(query);
			//문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
			//바인딩
			pstmt.setString(1, "김영하"); //1번 물음표
			pstmt.setString(2, "알쓸신잡"); //2번 물음표
			
			//실행
			int count = pstmt.executeUpdate(); //쿼리문 실행
		    // 4.결과처리
			System.out.println(count + "건이 저장되었습니다.");
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		                   
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
