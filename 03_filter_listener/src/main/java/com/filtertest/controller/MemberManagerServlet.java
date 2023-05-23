package com.filtertest.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberManagerServlet
 */
//@WebServlet("/admin/membermanager.do")
@WebServlet(name="memberManager", urlPatterns="/admin/membermanager.do")
public class MemberManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MemberManagerServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("회원관리 기능");
		System.out.println("관리자만 이용할 수 있는 서비스");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
