//Dao만들기, AuthorVo사용하기 , 공통변수 빼기

package com.javaex.ex04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AuthorDao {
	//필
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe" ;
	private String id = "webdb";
	private String pw = "webdb";
	//생
	public AuthorDao() {//생략가능
	}
	
	
	
	//메겟셋
	
	//메일반
	
	
	
	public void authorInsert(AuthorVo authorVo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		/*ResultSet rs = null;*/

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

		    // 2. Connection 얻어오기
		
			conn = DriverManager.getConnection(url, id, pw);

		    // 3. SQL문 준비 / 바인딩 / 실행
			//문자열 만들기
			String query = ""; 
		    query += " insert into author ";    
			query += " values(seq_author_id.nextval, ?, ?) ";
			System.out.println(query);
			//문자열로 쿼리문 만들기
			pstmt = conn.prepareStatement(query);
			//바인딩
			pstmt.setString(1, authorVo.getAuthorName()); //1번 물음표
			pstmt.setString(2, authorVo.getAuthorDesc()); //2번 물음표
			//실행
			int count = pstmt.executeUpdate(); //쿼리문 실행
		    // 4.결과처리
			System.out.println(count + "건이 저장되었습니다.(작가)");
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {/*
		        if (rs != null) {
		            rs.close();
		        } */               
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
	public void authorDelete(int index) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

		    // 2. Connection 얻어오기
			
			conn = DriverManager.getConnection(url, id, pw);

		    // 3. SQL문 준비 / 바인딩 / 실행
			//문자열 만들기
			String query = "";
			
			query += " delete from author ";
			query += " where author_id = ? ";
			System.out.println(query);
			//문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, index);
			//실행
			int count = pstmt.executeUpdate();
		    
		    // 4.결과처리
			System.out.println(count + "건이 실행되었습니다");
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
	
	
	public void authorUpdate (AuthorVo authorVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
	

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

		    // 2. Connection 얻어오기
			
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("접쏙썽공");
		    // 3. SQL문 준비 / 바인딩 / 실행
		    
			//문자열 만들기
			String query = "";
			query += " update author ";
			query += " set author_name = ?, ";
			query += " author_desc = ? ";
			query += " where author_id = ? ";
			System.out.println(query);
			
			//문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
			//바인딩
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());
			pstmt.setInt(3, authorVo.getAuthorId());

			//실행
			int count = pstmt.executeUpdate();
		    // 4.결과처리
			System.out.println(count + "건이 실행되었습니다");
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
	//작가 리스트 가져오기
	
	
	
	public List<AuthorVo> authorSelect(){//오라클db에서 모든 작가 정보 가져오는 코드-> ㄹ
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		//작가 데이터 가져오기
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

		    // 2. Connection 얻어오기
		
			conn = DriverManager.getConnection(url,id , pw);

		    // 3. SQL문 준비 / 바인딩 / 실행
			//문자열 만들기
			String query = "";
			query += " select author_id, ";
			query += " author_name, ";
			query += " author_desc ";
			query += " from author ";
			System.out.println(query);
			//문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
		    
			//?? 없어서 바인딩 생략
			
			//실행
		    rs = pstmt.executeQuery();  //여기 업데이트랑 햇갈리니 꼭 주의
		    
			// 4.결과처리
		    while(rs.next()) {
		   
		    int authorId = rs.getInt("author_id");  //<--별명 지정해줬을시 여기에 별명 잘 넣어주어야함 한글 사용 x
		   
		    String authorName = rs.getString("author_name");
		    String authorDesc = rs.getString("author_desc");
		
		    AuthorVo vo = new AuthorVo(authorId, authorName, authorDesc);
		    authorList.add(vo);
		    
		    //System.out.println(authorId +"\t"+ authorName +"\t"+ authorDesc);         // \t 자동 줄맞춤
		    }
		    
		    
		    //출력
		    for(int i=0; i<authorList.size();  i++) {
		    	AuthorVo authorVo = authorList.get(i);
		    	System.out.println(authorVo.getAuthorId() +","+ authorVo.getAuthorName() +","+ authorVo.getAuthorDesc());
		    }
		    //첫번째 작가 다시출력
		    AuthorVo authorVo = authorList.get(0);
		    System.out.println(authorVo.getAuthorName());
		    
		    
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
		return authorList;
	}
}
