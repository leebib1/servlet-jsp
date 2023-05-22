package com.servlet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//일반 클래스 서블릿 역할(클라이언트의 요청을 받는 클래스)로 만들기
//1. javax.servlet.http.HttpServlet클래스를 상속받는다.
//2. HttpServlet에 정의되어있는 메소드를 재정의(오버라이드)한다.
//	1)doGet() : 클라이언트가 get방식으로 요청한 내용을 처리하는 메소드
//	2)doPost() : 클라이언트가 post방식으로 요청한 내용을 처리하는 메소드
//3. 서버에 요청을 받을 수 있게 등록을 해야한다. ->web.xml파일이나 @어노테이션을 이용
//	1)생성한 서블릿 클래스를 서버에 등록
//	2)서블릿이 실행할 주소를 매핑(연결)
public class BasicServletController extends HttpServlet {
	//인터페이스 구현
	private static final long serialVersionUID = 5412475190290065154L;
	
	public BasicServletController() {
		
	}
	
	//exeption 처리 해줘야함.
	//기본은 protected로 선언
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("서블릿의 doGet메소드가 실행됨");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("서블릿의 doPost메소드가 실행됨");
	}
	
	
	
	
	
	
	
	
	
}
