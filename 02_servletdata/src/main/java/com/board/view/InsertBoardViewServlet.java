package com.board.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class insertBoardView
 */
@WebServlet("/insertBoardView.do")
public class InsertBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertBoardViewServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int boardNo = (int) (Math.random() * 10 + 1);
		String title = (String) request.getParameter("title");
		String writer = (String) request.getParameter("writer");
		String content = (String) request.getParameter("content");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String html = "";
		html += "<html><head><title>게시글 조회</title></head>";
		html += "<body>";
		html += "<h2>" + boardNo + " "+ title +" " + writer + "</h2>";
		html += "<p>" + content + "</p>";
		html += "</body></html>";
		out.write(html);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
