package com.web.board.model.service;

import java.sql.Connection;
import java.util.List;

import com.web.board.dto.Board;
import com.web.board.model.dao.BoardDao;

import static com.web.common.JDBCTemplate.*;

public class BoardService {
	private BoardDao dao=new BoardDao();
	
	//전체 컬럼 수
	public int selectBoardCount() {
		Connection conn=getConnection();
		int count=dao.selectBoardCount(conn);
		close(conn);
		return count;
	}

	//게시글 전체 조회
	public List<Board> selectBoardList(int cPage, int numPerpage) {
		Connection conn=getConnection();
		List<Board> boardList=dao.selectBoardList(conn, cPage, numPerpage);
		close(conn);
		return boardList;
	}

	//게시글 상세 조회
	public Board selectBoardContent(int boardNo) {
		Connection conn=getConnection();
		Board b=dao.selectBoardContent(conn, boardNo);
		close(conn);
		return b;
	}

	public int insertBoard(Board b) {
		Connection conn=getConnection();
		int result=dao.insertBoard(conn, b);
		if(result==1) commit(conn);
		else rollback(conn);
		return result;
	}

}
