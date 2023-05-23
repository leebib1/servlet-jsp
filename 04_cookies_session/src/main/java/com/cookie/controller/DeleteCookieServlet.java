package com.cookie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteCookieServlet
 */
@WebServlet("/deletecookie.do")
public class DeleteCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DeleteCookieServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//cookie 삭제
		//삭제할 쿠키와 동일한 key의 cookie객체를 생성하고 setMaxAge(0)으로 설정한다.
		//key는 동일한 값을 가질 수 없으므로 동일한 key값이 있는 경우 덮어쓰기 된다.
		Cookie c=new Cookie("cookiedata","");
		c.setMaxAge(0);
		response.addCookie(c);
		response.sendRedirect(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
