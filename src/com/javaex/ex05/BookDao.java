package com.javaex.ex05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
	
	//인서트
	public void BookInsert(String title, String pubs, String pubDate, int authorId) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
	

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속완료요");

		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "";
		    query += " insert into book ";
		    query += " values (SEQ_BOOK_ID.nextval, ?, ?, ?, ?) ";
		
		    //문자열을 쿼리문으로
		    pstmt = conn.prepareStatement(query);
		    //바인딩
		    pstmt.setString(1, title);
		    pstmt.setString(2, pubs);
		    pstmt.setString(3, pubDate);
		    pstmt.setInt(4, authorId);
		    
			
			
		    // 4.결과처리
		    int count = pstmt.executeUpdate();
		    System.out.println(count +"건이 저장되었습니다");

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
///////////////////////////////////////////////업데이트
	
	public void Bookupdate(int bookId, String title, String pubs, String pubDate, int authorId) {
		// 0. import java.sql.*;
				Connection conn = null;
				PreparedStatement pstmt = null;
			

				try {
				    // 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName("oracle.jdbc.driver.OracleDriver");

				    // 2. Connection 얻어오기
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					conn = DriverManager.getConnection(url, "webdb", "webdb");
					System.out.println("업뎃접속완료요");
				    // 3. SQL문 준비 / 바인딩 / 실행
				    String query = "";
				    query += " update book ";
				    query += " set title = ?, ";
				    query += " pubs = ?, ";
				    query += " pub_date = ?, ";
				    query += " author_id = ? ";
				    query += " where book_id = ?";
				    
				    //문자를 쿼리로
				    pstmt = conn.prepareStatement(query);
				    //바인딩
				    pstmt.setString(1, title);
				    pstmt.setString(2, pubs);
				    pstmt.setString(3, pubDate);
				    pstmt.setInt(4, authorId);
				    pstmt.setInt(5, bookId);
				    // 4.결과처리
				    int count = pstmt.executeUpdate();
				    System.out.println(count + "건이 업데이트 되었습니다.");
				    
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
	//딜리트
	public void BookDelete(int index) {
		// 0. import java.sql.*;
				Connection conn = null;
				PreparedStatement pstmt = null;
			

				try {
				    // 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName("oracle.jdbc.driver.OracleDriver");

				    // 2. Connection 얻어오기
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					conn = DriverManager.getConnection(url, "webdb", "webdb");
					System.out.println("딜리트접속 성공");
				    // 3. SQL문 준비 / 바인딩 / 실행
					String query = "";
					query += " delete from book ";
					query += " where book_id = ? ";
					
					//문자를 쿼리문으로
					pstmt = conn.prepareStatement(query);
					
					//바인딩
					pstmt.setInt(1, index);
				    
				    // 4.결과처리
					int count = pstmt.executeUpdate();
					System.out.println(count + "건이 삭제되었습니다.");
					
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
	
	//셀렉트
	
	public List<BookVo> BookSelect() {
		
		List<BookVo> bookList = new ArrayList<BookVo>();
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
					System.out.println("select 접속완료요");
				    // 3. SQL문 준비 / 바인딩 / 실행
					String query = "";
					query += " select book_id, ";
					query += " title, ";
					query += " pubs, ";
					query += " pub_date, ";
					query += " author_id ";
					query += " from book ";
					
					//문자를 쿼리로
					pstmt = conn.prepareStatement(query);
				    
					rs = pstmt.executeQuery();
					//출력
					while(rs.next()) {
						int bookId = rs.getInt("book_id");
						String title = rs.getString("title");
						String pubs = rs.getString("pubs");
						String pubDate = rs.getString("pub_date");
						int authorId = rs.getInt("author_id");
						
						BookVo vo = new BookVo(bookId, title, pubs, pubDate, authorId);
						bookList.add(vo);
					
						//출력
						for(int i=0; i<bookList.size(); i++) {
							BookVo bookVo = bookList.get(i);
							System.out.println(bookVo.getBookId() +"\t"+ bookVo.getTitle() +"\t"+ bookVo.getPubs() +"\t"+ bookVo.getPubDate() +"\t"+ bookVo.getAuthorId());
						}
						
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
				return bookList;
				
	}

}
