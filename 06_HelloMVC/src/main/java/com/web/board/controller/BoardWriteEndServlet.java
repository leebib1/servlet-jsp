package com.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		Board b=Board.builder().boardTitle(request.getParameter("board_title"))
				.boardWriter(request.getParameter("board_writer"))
				.boardOriginalFileName(request.getParameter("board_file"))
				.boardContent(request.getParameter("board_content")).build();
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
