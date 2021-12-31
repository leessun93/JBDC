package com.javaex.ex06;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		List<BookVo> list;
		BookDao bookdao = new BookDao();
		 
		BookVo bookvo = new BookVo();
		//입력
		
		BookVo vo01 = new BookVo("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
		
		bookdao.BookInsert(vo01);
		
		BookVo vo02 = new BookVo("삼국지", "민음사", "2002-03-01", 1);
		bookdao.BookInsert(vo02);
		BookVo vo03 = new BookVo("토지", "마로니에북스","2012-08-15", 2);
		bookdao.BookInsert(vo03);
		
		list = bookdao.BookSelect();
		
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).getBookId() +"\t"+
								list.get(i).getTitle() +"\t"+
								list.get(i).getPubs() +"\t"+
								list.get(i).getPubDate() +"\t"+
								list.get(i).getAuthorId());
		}
		
		
		System.out.println("===============================================================");
		
		
		
		//수정
		bookdao.Bookupdate(1, "우리들의 빻아버린 영웅", "다려짐", "1993-06-01", 1);
		
		for(int i=0; i<bookList.size(); i++) {
			System.out.println(bookList.get(i).getBookId() +"\t"+
								bookList.get(i).getTitle() +"\t"+
								bookList.get(i).getPubs() +"\t"+
								bookList.get(i).getPubDate() +"\t"+
								bookList.get(i).getAuthorId());
		}
		
		//삭제
		bookdao.BookDelete(1);
		
		for(int i=0; i<bookList.size(); i++) {
			System.out.println(bookList.get(i).getBookId() +"\t"+
								bookList.get(i).getTitle() +"\t"+
								bookList.get(i).getPubs() +"\t"+
								bookList.get(i).getPubDate() +"\t"+
								bookList.get(i).getAuthorId());
		}
		
	}

}
