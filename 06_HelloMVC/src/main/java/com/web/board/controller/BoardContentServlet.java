package com.web.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.dto.Board;
import com.web.board.dto.BoardComment;
import com.web.board.model.service.BoardService;


@WebServlet("/board/boardContent")
public class BoardContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardContentServlet() {
    }

	//게시글 상세 조회 서블릿
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo=Integer.parseInt(request.getParameter("boardNo"));
		//조회수 증가 조절하기
		//cookie가 덮어쓰기 되지 않게 이전 값을 확인
		Cookie[] cookies=request.getCookies();
		String boardRead="";
		boolean isRead=false;
		if(cookies!=null) {
			for(Cookie c:cookies) {
				if(c.getName().equals("boardRead")) {
					boardRead=c.getValue();
					if(boardRead.contains("|"+boardNo+"|")) {
						isRead=true;
					}
					break;
				}
			}
		}
		if(!isRead) {
			Cookie c=new Cookie("boardRead",boardRead+"|"+boardNo+"|");
			c.setMaxAge(60*60*24);
			response.addCookie(c);			
		}
		
		Board b=new BoardService().selectBoardContent(boardNo, isRead);
		request.setAttribute("board", b);
		List<BoardComment> comments=new BoardService().selectBoardComment(b.getBoardNo());
		request.setAttribute("comments", comments);
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
