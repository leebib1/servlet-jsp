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
		Map<String,String[]> param=req.getParameterMap();
		for(String key:param.keySet()) {
			System.out.println(key+" : "+Arrays.toString(param.get(key)));
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	

}
