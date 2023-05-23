package com.filtertest.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/basicfilter.do")
public class BasicFilterTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public BasicFilterTestServlet() {
    	
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("서블릿 실행");
		
		HttpSession session=request.getSession();
		session.setAttribute("loginId", "admin");
		
		request.setAttribute("event", "값넣기");
		
		response.sendRedirect(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
