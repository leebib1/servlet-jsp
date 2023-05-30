package com.web.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdatePasswordServlet
 */
@WebServlet("/member/updatePassword.do")
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdatePasswordServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//비밀번호를 변경하는 서블릿
		//비밀번호를 입력 받을 화면을 열어준다.
		request.getRequestDispatcher("/views/member/updatePwd.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
