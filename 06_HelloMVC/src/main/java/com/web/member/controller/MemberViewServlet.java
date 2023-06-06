package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.common.AESEncryptor;
import com.web.member.model.dto.Member;
import com.web.member.model.service.MemberService;

/**
 * Servlet implementation class MemberViewServlet
 */
@WebServlet(name="memberView", urlPatterns = "/member/memberView.do")
public class MemberViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MemberViewServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//내정보 확인
		//회원 정보를 보여주는 화면으로 이동시킴
		//아래처럼 작성하면 url에 사용자 아이디를 입력했을 때 회원 정보에 전부 접속 가능해진다. 보안성 문제 취약
		String userId=request.getParameter("userId");
		Member m=new MemberService().selectByUserId(userId);
		try {
			//원래 따로 쓴다. try안에서 위에서 먼저 예외가 발생하면 아래 작성한 것도 실행이 안 된다.
			m.setEmail(AESEncryptor.decryptData(m.getEmail()));
			m.setPhone(AESEncryptor.decryptData(m.getPhone()));
		}catch(Exception e) {
			System.out.println("복호화 실패했습니다.");
		}
//		HttpSession session=request.getSession();
//		Member member=(Member)session.getAttribute("loginMember");
		//1. DB에 로그인한 회원의 정보를 가져온다.
		//로그인된 정보로 접속하게 만들기
//		Member m=new MemberService().selectByUserId(member.getUserId());
		request.setAttribute("infoMember", m);
		//2. 전달 받은 데이터를 화면에 출력
		request.getRequestDispatcher("/views/member/memberView.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
