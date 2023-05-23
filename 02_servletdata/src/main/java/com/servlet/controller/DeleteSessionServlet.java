package com.servlet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeleteSessionServlet
 */
@WebServlet("/deleteSession.do")
public class DeleteSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DeleteSessionServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		session.invalidate(); //session 값 삭제. invalidate()를 사용하기 전까지 session객체는 살아있게된다.
//		response.sendRedirect("checkData.do"); //상대 경로 : 현재 경로에서 뒤에만 변경함
		response.sendRedirect("/02_servletdata/checkData.do");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
