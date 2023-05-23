package com.servlet.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/extraInfo.do")
public class RequestExtraDataInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RequestExtraDataInfoServlet() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpServletRequest객체가 제공하는 정보들
		//1.ContextRoot 가져오기
		//경로가 달라져도 하나씩 수정하지 않고 변경된 값으로 가져올 수 있다. 좀더 추상적으로 작성 가능
		String contextPath=request.getContextPath();
		System.out.println(contextPath);
		
		//2.HttpRequest의 header 정보 가져오기
		String userAgent=request.getHeader("User-Agent");
		System.out.println(userAgent);
		//이전 페이지 정보
		//파싱해서 필요한 뒤에 주소만 가져가 쓴다. contextroot를 이용해서 잘라낸다.
		String prevPage=request.getHeader("Referer"); 
		System.out.println(prevPage);
		
		//3.요청한 주소에 대한 정보를 가져오기
		String uri=request.getRequestURI();
		System.out.println(uri);
		StringBuffer url=request.getRequestURL();
		System.out.println(url);
		
		System.out.println(request.getCookies());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
