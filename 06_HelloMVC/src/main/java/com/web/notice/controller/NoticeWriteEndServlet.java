package com.web.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.web.notice.dto.Notice;
import com.web.notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeWriteServlet
 */
@WebServlet("/notice/noticewriteend.do")
public class NoticeWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public NoticeWriteEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	//공지사항 작성 서블릿
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파일은 나중에 추가
		//1. multipart/form-data 형식의 요청인지 확인
		if(!(ServletFileUpload.isMultipartContent(request))) {
			//해당 타입이 맞으면 true, 아니면 false를 반환
			request.setAttribute("msg", "잘못된 접근입니다. 관리자에게 문의하세요.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return; //multipart타입이 아니면 리턴 처리
		}
		
		//if문에 들어가지 않았으면
		//2. 데이터 업로드 처리
		//cos.jar에서 제공하는 MultipartRequest 클래스를 이용해서 처리한다.
		//MulipartRequest 클래스를 생성하면 자동으로 request에 담겨있는 데이터(file)를 지정된 위치에 저장한다.
		//매개변수 있는 생성자를 이용해서 생성한다.
		//1 : HttpServletRequest
		//2 : 파일을 저장할 위치 ->절대 경로를 String으로 넣는다.
		//3 : 업로드 파일의 최대 크기를 int형으로 지정한다. ->byte 단위로 한다. 1024(byte)*1024=MB, MB*1024=GB
		//4 : 인코딩 방식을 String으로 지정한다.(utf-8)
		//5 : rename 규칙 설정을 클래스로 지정한다. ->기본 제공되는 DefaultFileRenamePolicy 클래스를 이용한다.(재정의 해서 사용도 가능하다)
		
		//저장할 위치 String으로 가져오기
		//ServletContext 객체를 이용해서 웹 어플리케이션의 절대 경로를 가져올 수 있다.
		//getServletContext().getRealPath("/") : webapp까지의 경로를 가져온다.
		String path=getServletContext().getRealPath("/upload/notice");
//		System.out.println(path);
		
		//최대 파일 크기 지정
		int maxSize=1024*1024*100; //100MB
		//인코딩 설정
		String encode="UTF-8";
		//rename 클래스 생성
		DefaultFileRenamePolicy dfr=new DefaultFileRenamePolicy();
		
		//MultipartRequest 클래스 생성
		//생성과 동시에 지정된 위치에 업로드된 파일을 저장시킨다.
		MultipartRequest mr=new MultipartRequest(request, path, maxSize, encode, dfr);
		
		//클라이언트가 보낸 데이터는 생성된 MultipartRequest 객체를 이용해서 가져온다.
		//getParameter("name값")
		String noticeTitle=mr.getParameter("notice_title");
		System.out.println(noticeTitle);
		//저정된 파일에 대한 정보 가져오기
		//원본파일명, 재정의한 파일명, 파일 사이즈 등의 정보를 가져올 수 있다.
		String oriFileName=mr.getOriginalFileName("notice_file");
		String renameFileName=mr.getFilesystemName("notice_file");
		System.out.println(oriFileName+" "+renameFileName);
		
		Notice notice=Notice.builder().noticeTitle(mr.getParameter("notice_title"))
				.noticeWriter(mr.getParameter("notice_writer"))
				.filepath(mr.getFilesystemName("notice_file"))
				.noticeContent(mr.getParameter("notice_content"))
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
