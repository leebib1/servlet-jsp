package com.filtertest.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.filtertest.common.filter.MyRequestWrapper;

/**
 * Servlet implementation class EncodingFilterTestServlet
 */
@WebServlet(name = "encoding", urlPatterns = "/data.do")
public class EncodingFilterTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public EncodingFilterTestServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyRequestWrapper mrw=new MyRequestWrapper(request); //매개변수 있는 생성자만 존재. 요청받은 값을 그대로 보내준다.
		
		String data=request.getParameter("data");
		String mydata=mrw.getParameter("data"); //재정의한 메소드가 실행
		System.out.println("data : "+data);
		System.out.println("mydata : "+mydata);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
