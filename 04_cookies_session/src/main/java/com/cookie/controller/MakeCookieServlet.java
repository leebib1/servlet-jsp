package com.cookie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MakeCookieServlet
 */
@WebServlet("/makecookie.do")
public class MakeCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MakeCookieServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//쿠키 생성 후 저장시키기
		Cookie c=new Cookie("cookiedata","cookie");
		c.setMaxAge(60*60*24); //cookie유지 기간을 지정 가능 초*분*시
		response.addCookie(c);
		
		response.sendRedirect(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
