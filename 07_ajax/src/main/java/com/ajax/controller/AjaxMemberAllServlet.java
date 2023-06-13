package com.ajax.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.admin.model.dao.AdminService;
import com.web.member.model.dto.Member;

/**
 * Servlet implementation class MemberListTestServlet
 */
@WebServlet("/memberAll.do")
public class AjaxMemberAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AjaxMemberAllServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Member> members=new AdminService().selectMembers(1,100);
//		members.stream().forEach(System.out::println);
//		request.setAttribute("members", members);
//		request.getRequestDispatcher("/views/memberView.jsp").forward(request, response);
		
		//csv 방식으로 데이터를 보내기
		//문자열로 전송하기 때문에 데이터를 구분할 수 있는 구분자가 명확하게 존재해야된다.
		String resultData=members.stream().map(e->e.toString()).collect(Collectors.joining("\n"));
		response.setContentType("text/csv;charset=utf-8");
		response.getWriter().print(resultData);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
