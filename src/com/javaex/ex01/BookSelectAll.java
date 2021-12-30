package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookSelectAll {

	public static void main(String[] args) {
		List<BookVo> authorList = new ArrayList<BookVo>();
		// 책+데이터 가져오기
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "";
		    query += " select book_id, ";
		    query += " title, ";
		    query += " pubs, ";
		    query += " to_char(pub_date, 'yyyy-mm-dd' ) pub_date, ";
		    query += " bo.author_id, ";
		    query += " author_name, ";
		    query += " author_desc " ;
		    query += " from book bo, author au ";
		    query += " where bo.author_id = au.author_id ";

		    //쿼리문으로 변환
		    pstmt = conn.prepareStatement(query);
		    
		    rs = pstmt.executeQuery();
		    //결과처리
		    while(rs.next()) {
		    	int bookId = rs.getInt("book_id");
		    	String title = rs.getString("title");
		    	String pubs = rs.getString("pubs");
		    	String pubDate = rs.getString("pub_date");
		    	int authorId = rs.getInt("author_id");
		    	String authorName = rs.getString("autor_name");
		    	String authorDesc = rs.getString("author_desc");
		    	
		    	//System.out.println(bookId + "\t" + title + "\t" + pubs + "\t" + pubDate + "\t" + authorId + "\t" + authorName + "\t" + authorDesc);
		    	
		    	
		    }
		    
		    
		    
		    // 4.결과처리

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (rs != null) {
		            rs.close();
		        }                
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
