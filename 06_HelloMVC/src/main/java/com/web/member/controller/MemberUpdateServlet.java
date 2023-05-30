package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.member.model.dto.Member;
import com.web.member.model.service.MemberService;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/member/updateEndMember.do")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MemberUpdateServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원 정보를 수정하는 서비스
		//1. 클라이언트가 보낸 데이터를 받아오기
		Member m=Member.builder().userId(request.getParameter("userId"))
				.userName(request.getParameter("userName"))
				.age(Integer.parseInt(request.getParameter("age")))
				.email(request.getParameter("email"))
				.gender(request.getParameter("gender").charAt(0))
				.phone(request.getParameter("phone"))
				.address(request.getParameter("address"))
				.hobby(request.getParameterValues("hobby"))
				.build();
		
		//2. DB에 있는 회원 정보를 수정
		int result=new MemberService().updateMember(m);
		String msg="",loc="";
		//3. 수정 결과를 출력
		if(result>0) {
			msg="회원 정보가 수정되었습니다.";
			loc="/"; //메인화면으로 돌아감
			//변경된 정보로 session에 저장될 수 있게 한다.
			HttpSession session=request.getSession();
			session.setAttribute("loginMember", new MemberService().selectByUserId(m.getUserId()));
		}else {
			msg="회원 정보 수정에 실패했습니다.";
//			loc="/views/member/memberView.jsp";
			//jsp로 바로 넘겨주면 request에 정보가 없기 때문에 데이터를 불러오면서 에러가 발생한다.
			//요청이 넘어가면서 페이지를 전환하는 경우 jsp를 불러오는 서블릿을 연결 해야한다.
//			loc="/member/memberView.do?userId="+m.getUserId();
			loc="/member/memberView.do";
		}
		request.setAttribute("msg", msg);
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
