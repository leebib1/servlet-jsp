package com.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.web.board.dto.Board;
import com.web.board.model.service.BoardService;

/**
 * Servlet implementation class BoardWriteEndServlet
 */
@WebServlet("/board/boardwriteEnd")
public class BoardWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardWriteEndServlet() {
    }

	//게시글 작성 서블릿
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//타입 확인
		if(!(ServletFileUpload.isMultipartContent(request))) {
			request.setAttribute("msg", "잘못된 접근입니다. 관리자에게 문의하세요.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
		String path=getServletContext().getRealPath("/upload/board");
		int maxSize=1024*1024*200; //200MB
		String encode="UTF-8";
		DefaultFileRenamePolicy dfr=new DefaultFileRenamePolicy();
		MultipartRequest mr=new MultipartRequest(request, path, maxSize, encode, dfr);
		String renamedFileName=mr.getFilesystemName(mr.getParameter("board_file"));
		
		Board b=Board.builder().boardTitle(mr.getParameter("board_title"))
				.boardWriter(mr.getParameter("board_writer"))
				.boardOriginalFileName(mr.getOriginalFileName("board_file"))
				.boardRenamedFileName(renamedFileName)
				.boardContent(mr.getParameter("board_content")).build();
		int result=new BoardService().insertBoard(b);
		String msg, loc;
		if(result>0) {
			msg="게시글 작성되었습니다.";
			loc="/board/boardList.do";
		}else {
			msg="게시글 작성 실패했습니다.";
			loc="/board/boardwrite";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
