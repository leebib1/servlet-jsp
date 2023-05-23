package com.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckDataServlet
 */
@WebServlet("/checkData.do")
public class CheckDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CheckDataServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestData=(String)request.getAttribute("requestdata");
		HttpSession session=request.getSession(); //객체 먼저 가져온다.
		String sessionData=(String)session.getAttribute("sessiondata"); //세션 객체에 접근해서 데이터를 가져온다.
		ServletContext context=request.getServletContext();
		String contextData=(String)context.getAttribute("contextdata");
		
		response.setContentType("text/html;charset=utf-8"); //html로 구분했기 때문에 html,body태그가 없어도 실행은 되지만 원래 적어야한다.
		PrintWriter out=response.getWriter();
		String html="<h3>request : "+requestData+"</h3>";
		html+="<h3>session : "+sessionData+"</h3>";
		html+="<h3>context : "+contextData+"</h3>";
		html+="<button onclick=\"location.assign('/02_servletdata/checkData.do')\">checkdata 재요청</button>";
		html+="<button onclick=\"location.assign('/02_servletdata/deleteSession.do')\">session 삭제</button>";
		out.write(html);
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
