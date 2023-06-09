package com.web.notice.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/fileDownload.do")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public FileDownloadServlet() {
    	
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//file 다운로드 기능 구현
		//1. 클라이언트가 보낸 파일 이름 받기
		String fileName=request.getParameter("filename");
		
		//2. 파일 절대 경로
		String path=getServletContext().getRealPath("/upload/notice/");
		File f=new File(path+fileName);
		
		//3. InputStream 인터페이스를 구현한 FileInputStream 클래스 이용
		FileInputStream fis=new FileInputStream(f);
		//속도를 위해 보조스트림 사용
		BufferedInputStream bis=new BufferedInputStream(fis);
		
		//4. 한글 파일명이 깨지지 않도록 인코딩 처리
		//다운로드도 rename으로 사용하고 rename 규칙을 영어+숫자 조합으로만 사용할 때는 필요 없다.
		String fileRename="";
		String header=request.getHeader("user-agent"); //사용자의 브라우저나 기본 정보를 불러옴
		//IE, 그외 브라우저랑 인코딩 처리 방식이 다르기 때문에 인코딩을 분리해서 처리해야 한다.
		boolean isMSIE=header.indexOf("MEIS")!=-1||header.indexOf("Trident")!=-1;
		if(isMSIE) {
			fileRename=URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
		}else {
			fileRename=new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
		}
		
		//5. 응답 해더 설정
		//contentType를 설정해줘야 한다.
		response.setContentType("application/octect-stream");
		//파일 타입 자체가 지정되지 않았을 때 처리하는 타입
		response.setHeader("Content-disposition", "attachment;filename="+fileRename);
		//attachment : 파일 경로에 알아서 다운로드 해준다.
		//index : 브라우저에서 열 수 있는 파일을 열어준다.
		//filename=다운로드될 파일명
		
		//6. 사용자에게 파일 전송
		//클라이언트의 스트림을 가져온다.
		ServletOutputStream sos=response.getOutputStream();
		BufferedOutputStream bos=new BufferedOutputStream(sos);
		int read=-1;
		while((read=bis.read())!=-1) {
			bos.write(read);
		}
		
		//Stream 닫기-
		bis.close();
		bos.close();
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
