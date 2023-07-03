package com.el.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.el.model.vo.Snack;


@WebServlet("/dataTest.do")
public class ElDataTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ElDataTestServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Snack s=Snack.builder().type("초콜렛").name("m&m").price(1000).weight(50).build();
		Snack s2=Snack.builder().type("사탕").name("츄파츕스").price(300).weight(10).build();
		Snack s3=Snack.builder().type("젤리").name("하리보").price(2000).weight(60).build();
		Snack s4=Snack.builder().type("과자").name("꼬북칩").price(3000).weight(200).build();
		List<Snack> list=List.of(s,s2,s3,s4);
		request.setAttribute("snacks", list);
		
		request.setAttribute("snack", "request 영역 과자");
		HttpSession session=request.getSession();
		session.setAttribute("snack", "맛있는 과자");
		ServletContext context=getServletContext();
		context.setAttribute("snack", "Context 영역 과자");
		
		request.getRequestDispatcher("/views/dataTest.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
