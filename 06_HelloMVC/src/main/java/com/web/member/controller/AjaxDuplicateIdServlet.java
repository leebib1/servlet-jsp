package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.member.model.dto.Member;
import com.web.member.model.service.MemberService;


@WebServlet("/ajaxDuplicateId.do")
public class AjaxDuplicateIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AjaxDuplicateIdServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		Member m=new MemberService().selectByUserId(userId);
		response.setContentType("text/csv;charset=utf-8");
		response.getWriter().print(m==null?true:false);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
