package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.member.model.dto.Member;
import com.web.member.model.service.MemberService;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/login.do")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 확인
		String id=request.getParameter("userId");
		String pwd=request.getParameter("password");
		
		//아이디 정보 저장
		//체크박스를 가져오면 체크되면 on, 아니면 null을 반환
		String saveId=request.getParameter("saveId");
		System.out.println(saveId);
		if(saveId!=null) {
			Cookie c=new Cookie("saveId",id);
			c.setMaxAge(60*60*24*7);
			response.addCookie(c);
		}else {
			Cookie c=new Cookie("saveId","");
			c.setMaxAge(0);
			response.addCookie(c);
		}
		
		Member loginMember=new MemberService().selectByUserIdAndPw(id,pwd);
		request.setAttribute("member", loginMember);
		System.out.println(loginMember);
		if(loginMember!=null) {
			//로그인 정보 저장
			HttpSession session=request.getSession();
			session.setAttribute("loginMember", loginMember);
			//메인화면으로 돌림
			response.sendRedirect(request.getContextPath());
		}else {
			//실패 메세지 출력
			request.setAttribute("msg", "아이디, 패스워드가 일치하지 않습니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
