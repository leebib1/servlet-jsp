package com.web.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.notice.dto.Notice;
import com.web.notice.model.service.NoticeService;


@WebServlet("/notice/noticeview.do")
public class noticeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public noticeViewServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Notice> noticelist=new NoticeService().selectNotices();
		request.setAttribute("noticelist", noticelist);
		request.getRequestDispatcher("/views/notice/noticeView.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
