package com.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.dto.Board;
import com.web.board.model.service.BoardService;


@WebServlet("/board/boardContent")
public class BoardContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardContentServlet() {
    }

	//게시글 상세 조회 서블릿
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo=Integer.parseInt(request.getParameter("boardNo"));
		Board b=new BoardService().selectBoardContent(boardNo);
		request.setAttribute("board", b);
		request.getRequestDispatcher("/views/board/boardContent.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
