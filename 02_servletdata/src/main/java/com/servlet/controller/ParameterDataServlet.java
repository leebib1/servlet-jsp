package com.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "paramdata",urlPatterns = {"/testperson.do"}) //기본경로 뒷부분만 url패턴에 작성ㄹ
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
		
		//클라이언트가 데이터를 입력하지 않은 상태로 submit하면 공란으로 넘어온다.
		//데이터를 입력하지 않은 걸 확인하려면 공란인지 아닌지 확인해서 넘겨준다.
		//checkbox는 체크를 안 한 것들은 데이터를 넘기지 않는다. ->배열이 null이 출력됨
		//찾으려는 key(name)값이 존재하지 않으면 getParameter메소드는 null을 반환한다.
		
		
		//클라이언트에게 보여줄 응답 데이터 작성
		//HttpServletResponse객체가 제공하는 메소드를 이용한다.
		//1. 응답 데이터를 작성
		//contentType을 설정한다. ->데이터를 주고 받을 때는 전부 String으로 하기 때문에 설정된 타입으로 구분할 수 있게 지정
		//MIMETYPE설정
		//setContentType("MIMETYPE설정");
		resp.setContentType("text/html;charset=utf-8"); //text로 된 html파일. 세미콜론 뒤에 타입지정 가능. 대소문자를 구분하지 않음.
		
		//2. 응답 데이터 전송
		//	1)문자열 데이터 : 문자열 스트림으로 전송 ->getWriter();
		//	2)바이너리 데이터 : 파일 스트림으로 전송 ->getOutputStream();
		PrintWriter out=resp.getWriter(); //PrintWriter 객체를 반환
		
		//3. 원하는 데이터 전송
		//out.write("<h3>내가 만든 첫 응답 페이지</h3>"); //text.html로 타입을 지정했기 때문에 태그를 알아서 적용한다.
		String html="<html>";
		html+="<head>";
		html+="<title>개인 취향 테스트</title>";
		html+="</head>";
		html+="<body>";
		html+="<h3>개인 취향 결과</h3>";
		html+="<h4>"+name+"님의 개인 취향 결과 확인</h4>";
		html+="<h4>당신의 이름은 "+name+"이고, 나이는 "+age+"살이고, 키는 "+height+"cm입니다.</h4>";
		html+="<h4>좋아하는 색은 <span style='color:"+color+"'>"+color+"</span>입니다.</h4>";
		html+="<ul>좋아하는 동물";
		for(String a:animals) {
			String src="";
			switch(a) {
			case "강아지" :src="data:image/jpeg;base64,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"; break;
			case "고양이" : src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ15zSdjbO9AVI7jG0_bVAT4bFYeMg3HaGqqQ&usqp=CAU"; break;
			case "펭귄" : src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSE-E_iEZe0tdNhwjQ_d5hL1i87Cm-4EKpf9w&usqp=CAU"; break;
			case "기린" : src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQl1dpne_mcvhM4v91vWi6jNiZJUOwGOJEGIQ&usqp=CAU"; break;
			case "라쿤" : src="data:image/jpeg;base64,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"; break;
			case "토끼" : src="data:image/jpeg;base64,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"; break;
			}
			html+="<li><img src='"+src+"' width=200 height=200></li>";
		}
		html+="</ul>";
		html+="<p>오늘의 점심은 "+lunch+"입니다.</p>";
		html+="<h3>당신은 "+info+"</h3>";
		html+="</body></html>";
		out.write(html);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req,resp);
	}
	

}
