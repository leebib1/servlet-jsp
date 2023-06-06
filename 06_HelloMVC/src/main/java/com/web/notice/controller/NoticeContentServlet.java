package com.web.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.notice.dto.Notice;
import com.web.notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeContentServlet
 */
@WebServlet("/notice/noticeContent.do")
public class NoticeContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public NoticeContentServlet() {
    	
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//공지사항 게시글 내용을 확인하는 서블릿
		int noticeNo=Integer.parseInt(request.getParameter("noticeNo"));
		Notice n=new NoticeService().selectNoticeContent(noticeNo);
		request.setAttribute("notice", n);
		request.getRequestDispatcher("/views/notice/noticeContent.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
