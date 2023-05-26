package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.member.model.dto.Member;
import com.web.member.model.service.MemberService;

/**
 * Servlet implementation class IdDuplateServlet
 */
@WebServlet("/member/idduplate.do")
public class IdDuplateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public IdDuplateServlet() {
    }

	//아이디 중복 확인.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		
		Member m=new MemberService().selectByUserId(userId);
		request.setAttribute("result", m);
		request.getRequestDispatcher("/views/member/idDuplicate.jsp").forward(request, response);
				
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
