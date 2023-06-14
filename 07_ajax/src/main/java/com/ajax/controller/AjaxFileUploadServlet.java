package com.ajax.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/fileUpload")
public class AjaxFileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AjaxFileUploadServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=getServletContext().getRealPath("/test/");
		MultipartRequest mr=new MultipartRequest(request, path, 1024*1024*10, "UTF-8", new DefaultFileRenamePolicy());
		String name=mr.getParameter("name");
		System.out.println(name);
		System.out.println(mr.getFilesystemName("upfile0"));
		System.out.println(mr.getOriginalFileName("upfile0"));
		
		//여러개 파일을 프론트에서 보내도 names를 가져와서 반복문을 이용해 파일을 전체 가져올 수 있다.
		List<Map<String,String>> files=new ArrayList();
		Enumeration<String> names=mr.getFileNames();
		while(names.hasMoreElements()) {
			String key=names.nextElement();
			System.out.println(key);
//			System.out.println(mr.getFilesystemName(key));
//			System.out.println(mr.getOriginalFileName(key));
			files.add(Map.of("rename", mr.getFilesystemName(key),"oriname",mr.getOriginalFileName(key)));
		}
		System.out.println(files);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
