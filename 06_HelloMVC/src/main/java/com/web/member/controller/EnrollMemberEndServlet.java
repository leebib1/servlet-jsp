package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.common.AESEncryptor;
import com.web.member.model.dto.Member;
import com.web.member.model.service.MemberService;

/**
 * Servlet implementation class EnrollMemberEndServlet
 */
@WebServlet("/member/enrollMemberEnd.do")
public class EnrollMemberEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollMemberEndServlet() {
    	
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원가입 완료 후 메인화면으로 전환시키는 서블릿
		//회원 정보 저장
		Member m=Member.builder().userId(request.getParameter("userId"))
		.passward(request.getParameter("password"))
		.userName(request.getParameter("userName"))
		.age(Integer.parseInt(request.getParameter("age")))
		.gender(request.getParameter("gender").charAt(0))
		.email(request.getParameter("email"))
		.phone(request.getParameter("phone"))
		.address(request.getParameter("address"))
		.hobby(request.getParameterValues("hobby"))
		.build();
		//회원 가입 데이터 중 대칭키 암호화 시킬 데이터들
		try {
			m.setEmail(AESEncryptor.encryptData(m.getEmail()));
		}catch(Exception e) {
			System.out.println("암호화 실패했습니다.");
		}
		try {
			m.setPhone(AESEncryptor.encryptData(m.getPhone()));
		}catch(Exception e) {
			System.out.println("암호화 실패했습니다.");
		}
		int result=new MemberService().enrollMember(m);
		
		String msg="",loc="";
		if(result>0) {
			msg="회원가입을 축하드립니다.";
			loc="";
		}else {
			msg="회원가입 실패했습니다. 다시 시도하세요.";
			loc="/member/enrollMember.do";
		}
		request.setAttribute("msg",msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
