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
 * Servlet implementation class NoticeWriteServlet
 */
@WebServlet("/notice/noticewrite.do")
public class NoticeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public NoticeWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	//공지사항 작성 서블릿
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파일은 나중에 추가
		Notice notice=Notice.builder().noticeTitle(request.getParameter("notice_title"))
				.noticeWriter(request.getParameter("notice_writer"))
				.noticeContent(request.getParameter("notice_content"))
				.build();
		int result=new NoticeService().insertNotice(notice);
		String msg="",loc="";
		if(result>0) {
			msg="작성 완료되었습니다.";
			loc="/notice/noticeview.do";
		}else {
			msg="작성 실패했습니다.";
			loc="/notice/noticewrite.do";
		}
		request.setAttribute("msg",msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
