package com.servlet.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/sharedata.do")
public class ShareDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public ShareDataServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//각 객체에 데이터 저장
		//1.HttpServletRequest객체에 저장
		request.setAttribute("requestdata", "requestDataTest");
		
		//2.HttpSession객체에 저장
		//	1)HttpSession객체 생성 : HttpServletRequest가 제공하는 getSession() 메소드를 이용한다.
		HttpSession session=request.getSession();
		//	2)HttpSession 객체에 접근해서 setAttribute()를 이용해 데이터 저장
		session.setAttribute("sessiondata", "sessionDataTest");
		
		//3.ServletContext객체에 저장
		//	1)ServletContext객체 생성 : HttpServletRequest가 제공하는 getServletContext() 메소드 이용한다.
		ServletContext context=request.getServletContext();
		context=getServletContext(); //HttpServletRequest를 상속 받고 있기 때문에 접근해서 사용하지 않아도 된다.
		//	2)setAttribute()를 이용해서 데이터 저장
		context.setAttribute("contextdata", "contextDataTest");
		
		RequestDispatcher rd=request.getRequestDispatcher("/checkData.do");
		rd.forward(request, response);
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
