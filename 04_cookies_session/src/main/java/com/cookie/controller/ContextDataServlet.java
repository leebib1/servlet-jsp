package com.cookie.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/contextdata.do")
public class ContextDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ContextDataServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//web.xml에 context-param으로 등록된 데이터 가져오기
		ServletContext context=getServletContext();
		String contextdata=context.getInitParameter("admin"); //context-name을 넣어서 가져온다
		System.out.println(contextdata);
		
		//서블릿 초기화 데이터 이용하기 : init-param
		String servletdata=getInitParameter("servletdata");
		System.out.println(servletdata);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
