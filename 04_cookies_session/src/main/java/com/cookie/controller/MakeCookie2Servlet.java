package com.cookie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MakeCookie2Servlet
 */
@WebServlet("/makecookie2.do")
public class MakeCookie2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MakeCookie2Servlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie c=new Cookie("readBoard","1$2$3$4");
		c.setMaxAge(60*60*24);
		response.addCookie(c);
		response.sendRedirect(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
