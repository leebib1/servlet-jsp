package com.servlet.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestDispatcherTestServlet
 */
@WebServlet("/requestdispatchertest.do")
public class RequestDispatcherTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public RequestDispatcherTestServlet() {
    	
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청 내용을 다른 서블릿으로 전환
		System.out.println("requestDispatcher서블릿 실행");
		//데이터를 저장하는 작업
		//HttpServletRequest객체가 제공하는 setAttribute()메소드 이용
		//key:value형식으로 저장
		//setAttribute("key값",obj(value값));
		//페이지에서 저장한 건 parameter로 받아오기 때문에 key값이 중복되도 상관없지만 혼동되지 않게 주의한다.
		request.setAttribute("testData", "개인 취향 테스트에 오신 걸 환영합니다."); //해당 서블릿에서 저장한 데이터
		
		
		//MVC의 V에 해당하는 로직을 처리하는 장소를 분할하기 때문에 해당 장소로 데이터를 넘겨서 서블릿을 전환함.
		//RequestDispatcher객체를 이용한 서블릿 이동
		//HttpServletRequest.getRequestDispatcher("(서블릿||jsp)주소");
		RequestDispatcher rd=request.getRequestDispatcher("/dispatcherView.do"); //해당 주소로 재요청을 보냄
		//해당 주소로 넘겨줄 때 맞는 서블릿을 찾지 못했기 때문에 requestdispatchertest.do 서블릿은 실행됐지만 404오류가 발생함.
		rd.forward(request, response); //받았던 정보를 그대로 재전송
		
		//dispatcherView.do가 응답했어도 url에 requestdispatcher가 바뀌지 않는 이유는 응답 받을 때까지 요청이 해당 하나 뿐이었기 때문이다.
		//요청은 하나에서 바뀌지 않되 응답 처리하는 부분만 계속 바뀌고, 요청이 바뀌지 않았기 때문에 받은 데이터를 그대로 쓸 수 있다.
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
