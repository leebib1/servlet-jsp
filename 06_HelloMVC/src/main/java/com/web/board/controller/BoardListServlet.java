package com.web.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.dto.Board;
import com.web.board.model.service.BoardService;

@WebServlet("/board/boardList.do")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardListServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// paging
		int cPage, numPerpage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1;
		}
		numPerpage = 10;
		String pageBar = "";
		int totalData = new BoardService().selectBoardCount();
		int totalPage = (int) Math.ceil((double) totalData / numPerpage);
		int pageBarSize = 5;
		int pageNo = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
		int pageEnd = pageNo + pageBarSize - 1;

		if (pageNo == 1) {
			pageBar += "<span>[이전]</span>";
		} else {
			pageBar += "<a href='" + request.getRequestURI() + "?cPage=" + (pageNo - 1) + "'>[이전]</a>";
		}

		while (!(pageNo > pageEnd || pageNo > totalPage)) {
			if (pageNo == cPage) {
				pageBar += "<span> " + pageNo + " </span>";
			} else {
				pageBar += "<a href='" + request.getRequestURI() + "?cPage=" + pageNo + "'>" + pageNo + "</a>";
			}
			pageNo++;
		}

		if (pageNo > totalPage) {
			pageBar += "<span>[다음]</span>";
		} else {
			pageBar += "<a href='" + request.getRequestURI() + "?cPage=" + pageNo + "'>[다음]</a>";
		}
		request.setAttribute("pageBar", pageBar);
		
		//DB에서 게시글 정보 가져오기
		List<Board> boardList=new BoardService().selectBoardList(cPage, numPerpage);
//		System.out.println(boardList);
		request.setAttribute("boardList", boardList);
		
		request.getRequestDispatcher("/views/board/boardList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
