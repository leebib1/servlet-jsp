package com.web.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.admin.model.dao.AdminService;
import com.web.notice.dto.Notice;
import com.web.notice.model.service.NoticeService;


@WebServlet("/notice/noticeview.do")
public class NoticeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public NoticeViewServlet() {
    }

	//공지사항 조회 서블릿
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//paging
		int cPage, numPerpage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
//		try {
//			numPerpage=Integer.parseInt(request.getParameter("numPerpage"));
//		}catch(NumberFormatException e) {
			numPerpage=5;
//		}
		String pageBar="";
		int totalData=new NoticeService().selectNoticeCount();
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getRequestURI()+"?cPage="+(pageNo-1)+"'>[이전]</a>";
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<span> "+pageNo+" </span>";
			}else {
				pageBar+="<a href='"+request.getRequestURI()+"?cPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getRequestURI()+"?cPage="+pageNo+"'>[다음]</a>";
		}
		request.setAttribute("pageBar", pageBar);
		
		//공지사항 DB 가져오기
		List<Notice> noticelist=new NoticeService().selectNotices(cPage, numPerpage);
		request.setAttribute("noticelist", noticelist);
		request.getRequestDispatcher("/views/notice/noticeView.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
