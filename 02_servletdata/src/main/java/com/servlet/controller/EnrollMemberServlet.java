package com.servlet.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "enrollmember",urlPatterns = {"/enrollMember.do"})
public class EnrollMemberServlet extends HttpServlet{
	private static final long serialVersionUID = 8943647513626935483L;
	public EnrollMemberServlet() {
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); //html파일의 인코딩 값과 동일하게 적용해야한다.
		Map<String,String[]> param=req.getParameterMap();
		for(String key:param.keySet()) {
			System.out.println(key+" : "+Arrays.toString(param.get(key)));
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
		//보낸 방식에 차이가 있어서 메소드가 나뉠뿐 해당 데이터 처리하는 로직이 같다면 doGet메소드를 불러와서 이용할 수 있다.
		//post는 바이트로 쪼개서 전송하기 때문에 그대로 받아올 경우 영문자,숫자 외에는 전부 깨지게 된다.
		//때문에 post방식으로 데이터를 받는 경우 인코딩 처리 해줘야한다.
		//HttpServletRequest.setCharacterEncoding()을 이용한다.
		
	}
	

}
