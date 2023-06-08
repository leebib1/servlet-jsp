package com.test.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.member.dto.Member;
import com.test.member.service.MemberService;


@WebServlet("/memberenrollend.do")
public class MemberEnrollEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MemberEnrollEndServlet() {
    }

	//회원 가입 서블릿
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Member m=Member.builder().userId(request.getParameter("userId"))
				.password(request.getParameter("password"))
				.userName(request.getParameter("userName"))
				.age(Integer.parseInt(request.getParameter("age")))
				.email(request.getParameter("email"))
				.phone(request.getParameter("phone"))
				.address(request.getParameter("address"))
				.hobby(request.getParameterValues("hobby")).build();
//		System.out.println(m.toString());
		int result=new MemberService().insertMember(m);
		if(result>0) {
			response.sendRedirect(request.getContextPath());
		}else {
			request.setAttribute("result", result);
			request.getRequestDispatcher("/views/member/memberenroll.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
