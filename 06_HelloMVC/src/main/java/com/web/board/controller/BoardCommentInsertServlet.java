package com.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.dto.BoardComment;
import com.web.board.model.service.BoardService;


@WebServlet("/board/insertComment.do")
public class BoardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardCommentInsertServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardComment bc=BoardComment.builder().boardRef(Integer.parseInt(request.getParameter("boardRef")))
				.level(Integer.parseInt(request.getParameter("level")))
				.boardCommentWriter(request.getParameter("boardCommentWriter"))
				.boardCommentContent(request.getParameter("content"))
				.boardCemmnetRef(Integer.parseInt(request.getParameter("boradCommentRef")))
				.build();
		int result=new BoardService().insertBoardComment(bc);
		String view;
		if(result>0) {
			view="/board/boardContent";
			response.sendRedirect(request.getContextPath()+view+"?boardNo="+bc.getBoardRef());
		}else {
			request.setAttribute("msg", "댓글 등록에 실패했습니다.");
			request.setAttribute("loc", "/board/boardList.do?boardNo="+bc.getBoardRef());
			view="/views/common/msg.jsp";
			request.getRequestDispatcher(view).forward(request,response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
