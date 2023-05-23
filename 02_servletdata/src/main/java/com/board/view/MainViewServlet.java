package com.board.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MainViewServlet
 */
@WebServlet("/mainView.do")
public class MainViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MainViewServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String loginId=(String)session.getAttribute("loginId");
		String html="";
		//로그인 여부를 구분
		if(loginId!=null) {
			html+="<h2>"+loginId+"님 환영합니다!</h2>";
		}else {
			html+="<h2>로그인 후 이용 가능합니다.</h2>";
		}
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(html);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
