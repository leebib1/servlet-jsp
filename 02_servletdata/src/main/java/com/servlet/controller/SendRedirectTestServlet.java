package com.servlet.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendRedirectServlet
 */
@WebServlet("/sendredirect.do")
public class SendRedirectTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SendRedirectTestServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpServletResponse객체가 제공하는 sendRedirect()메소드 이용
		//requestdispatcher와 다르게 응답은 먼저 실행한다. 응답을 한 후 다른 곳에 요청을 다시 보냄.
		//sendRedirect메소드가 실행되면 자동으로 다시 요청을 보낸다.
		System.out.println("sendredirectTestServelt 실행!");
		response.sendRedirect("dispatcherView.do"); //슬래쉬를 빼고 상대경로로 시작. 해당 페이지로 전환된다.
		//요청이 바뀌면서 첫번째 요청에서 받은 값을 그대로 이용할 수 없기 때문에 null값을 형변환 하면서 에러가 발생한다.
		//회원가입, 상품등록 같은 화면에서 DB에 데이터를 저장하고 나서 더이상 해당 데이터를 사용할 일이 없을 때 사용한다.
		//요청이 전환되지 않으면 해당 데이터가 그대로 유지돼서 새로 고침할 때마다 게시글이 다시 작성되는 상황이 발생할 수 있음.
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
