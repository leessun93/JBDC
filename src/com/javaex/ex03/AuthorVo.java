package com.javaex.ex03;

public class AuthorVo {

	// 필
	private int authorId;
	private String authorName;
	private String authorDesc;

	// 생
	public AuthorVo() {

	}

	
	
	public AuthorVo(String authorName, String authorDesc) {
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}



	public AuthorVo(int authorId, String authorName, String authorDesc) {

		this.authorId = authorId;
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}
	
	
	// 매개세
	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorDesc() {
		return authorDesc;
	}

	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}



	// 매일
	
	
}
