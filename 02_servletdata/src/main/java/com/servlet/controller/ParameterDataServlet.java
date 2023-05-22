package com.servlet.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//어노테이션 방식으로 servlet을 등록
//class선언부에 @WebServlet어노테이션을 선언, annotation.WebServlet을 import한다.
//@WebServlet 어노테이션의 속성 설정으로 urlPatterns, name을 설정할 수 있다. ->매개변수로 받음
//name:servlet-name, urlPatterns:배열로 작성 가능하지만 {} 안에 문자열로 해당하는 url패턴을 작성해도 된다.
//외부에서 가져오는 servlet을 사용할 때는 수정이 불가능하기 때문에 해당 방법을 이용할 수 없다.
@WebServlet(name = "paramdata",urlPatterns = {"/testperson.do"})
//->해당 방법은 web.xml에 작성한 것과 동일하므로 작성 시 서버를 내렸다 올려줘야한다.
public class ParameterDataServlet extends HttpServlet {
	private static final long serialVersionUID = -7348993035964273633L;
	
	public ParameterDataServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//클라이언트가 보낸 데이터 확인
		//1. 단일 데이터 가져오기
		//HttpServletRequest.getParameter("key값") 이용
		String name=req.getParameter("name");
//		System.out.println(name);
		int age=Integer.parseInt(req.getParameter("age"));
		double height=Double.parseDouble(req.getParameter("height"));
		String color=req.getParameter("color");
		//2. 다수 데이터 가져오기
		//클라이언트가 동일한 key로 다수의 값을 보낸 데이터는 HttpServletRequest.getParameterValues("key값") 이용
		//String배열을 반환한다.
		String animal=req.getParameter("animal");
		String[] animals=req.getParameterValues("animal");
		String lunch=req.getParameter("lunch");
		String info=req.getParameter("info");
		
		System.out.println("이름 : "+name);
		System.out.println("나이 : "+age);
		System.out.println("키 : "+height);
		System.out.println("색상 : "+color);
		System.out.println("동물 : "+animal);
		System.out.print("동물들 : ");
		Arrays.asList(animals).stream().forEach(System.out::print);
		System.out.println();
		// :: 메소드 참조 받는 매개변수를 print뒤에 넣어준다.
		System.out.println("점심 : "+lunch);
		System.out.println("소개 : "+info);
		
		//3.클라이언트가 보낸 key값을 모를 때 key값을 가져오는 방법
		//HttpServletRequest.getParameterNames()를 이용한다.
		Enumeration<String> paramName=req.getParameterNames();
		while(paramName.hasMoreElements()) {//key값을 하나씩 뽑아온다.
			String key=paramName.nextElement(); //iterator와 비슷한 방식
//			System.out.println(key);
//			String value=req.getParameter(key); ->배열을 가져올 수 없다.
			String[] value=req.getParameterValues(key); //단일값도 배열로 받아와도 된다.
			System.out.println(key+" : "+Arrays.toString(value));
		}
		
		//4. Map방식으로 데이터를 가져오기
		Map<String,String[]> param=req.getParameterMap();
		for(String key:param.keySet()) {
			System.out.println(key+" : "+Arrays.toString(param.get(key)));
		}
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	

}
