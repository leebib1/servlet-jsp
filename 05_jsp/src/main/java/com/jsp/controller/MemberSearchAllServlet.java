package com.jsp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.model.dto.MemberDTO;
import com.jsp.model.service.MemberService;

/**
 * Servlet implementation class MemberSearchAllServlet
 */
@WebServlet("/memberAll.do")
public class MemberSearchAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MemberSearchAllServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DB student 계정의 MEMBER테이블에 있는 모든 데이터를 조회하는 기능
		//1. DB에서 필요한 데이터를 가져온다 : JDBC
		List<MemberDTO> members=new MemberService().selectMemberAll();
		//조회한 데이터를 화면에 전달하기 위해 넘길 데이터를 저장.
		request.setAttribute("members", members);
		request.getParameter("keyword");
		request.setAttribute("keyword", request.getParameter("keyword"));
		//2. DB 정보를 출력할 화면을 지정 : JSP
		//출력만 하고 유지될 필요 없는 데이터로 request를 이용
		//requestDispatcher에 절대 경로를 작성하면 무조건 webapp에서 찾는다
//		HttpSession session=request.getSession(true);
//		session.setAttribute("keyword",request.getParameter("keyword"));
		
		RequestDispatcher rd=request.getRequestDispatcher("/views/memberlist.jsp");
		rd.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
