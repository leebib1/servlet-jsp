package com.session.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionTestServlet
 */
@WebServlet("/sessiontest.do")
public class SessionTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//getSession메소드에 boolean형 매개변수를 설정할 수 있다.
		//true : 일치되는 session객체가 있으면 가져오고 없으면 생성 해서 가져온다.
		//false : 일치되는 session 객체가 있으면 가져오고 없으면 null을 반환한다.
		HttpSession session=request.getSession(true);
		System.out.println(session);
		session.setMaxInactiveInterval(5); //초 기준. 내부적으로 갖고 있는 session객체를 삭제하기 때문에 JSESSIONID가 유지되는 것과는 별개다.
		session.setAttribute("data", "sessionData");
		response.sendRedirect(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
