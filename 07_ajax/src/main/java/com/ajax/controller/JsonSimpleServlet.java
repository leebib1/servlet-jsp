package com.ajax.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject; //jsonsimple 라이브러리

import com.web.admin.model.dao.AdminService;
import com.web.member.model.dto.Member;

/**
 * Servlet implementation class JsonSimpleServlet
 */
@WebServlet("/basicJson.do")
public class JsonSimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public JsonSimpleServlet() {
    }

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Member> members=new AdminService().selectMemberByKeyword("userId", "a", 1, 30);
		Member m=members.get(0);
		System.out.println(m);
		
		//jsonsimple 라이브러리를 이용해서 간편하게 객체를 전송할 수 있다.
		//단일 객체 전송 : JSONObject 클래스 이용
		//다수 객체 전송 : JSONArray 클래스 이용
		JSONObject jo=new JSONObject();
		//JSONObject가 제공하는 멤버 메소드를 이용해서 전송할 데이터를 저장한다.
		//key:value 형식으로 저장한다.
		//put() 메소드 제공
		jo.put("userId", m.getUserId());
		jo.put("userName", m.getUserName());
		jo.put("age", m.getAge());
		jo.put("height", 180.5);
		jo.put("flag", true);
		
		//다수의 데이터를 json으로 전송
		JSONArray joa=new JSONArray();
		//JSONArray에는 리스트 방식으로 JSONObject를 저장할 수 있다.
		//add() 메소드를 이용한다.
		for(Member m1:members) {
			JSONObject j=new JSONObject();
			j.put("userId", m1.getUserId());
			j.put("userName", m1.getUserName());
			j.put("age", m1.getAge());
//			j.put("gender", m1.getGender()); //char 자료형은 파싱하지 못하기 때문에 String.valueOf()를 사용해서 자료형을 변환해서 보내야한다.
			j.put("phone", m1.getPhone());
			
			joa.add(j);
		}
		
		//생성된 JSONObject 객체를 전송
		response.setContentType("application/json;charset=utf-8");
		//response.getWriter().print(jo);
		response.getWriter().print(joa);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
