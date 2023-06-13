package com.ajax.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.web.admin.model.dao.AdminService;
import com.web.member.model.dto.Member;

/**
 * Servlet implementation class GsonTestServlet
 */
@WebServlet("/gsontest.do")
public class GsonTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GsonTestServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> names=request.getParameterNames();
		while(names.hasMoreElements()) {
			System.out.println(names.nextElement());
		}
		
		List<Member> list=new AdminService().selectMemberByKeyword("userId","a", 1, 30);
		Member m=list.get(0);
		//Gson 라이브러리를 이용해서 json 파싱
		//Gson 클래스를 생성한다.
		Gson gson=new Gson();
		//파싱하는 toJson(파싱할 객체,[outputStream]) 메소드 사용
		response.setContentType("application/json;charset=utf-8");
		//gson.toJson(m,response.getWriter());
		//m을 key값으로 사용한다.
		gson.toJson(list,response.getWriter());
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
