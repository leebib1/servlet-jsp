package com.web.board.model.dao;

import static com.web.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.web.board.dto.Board;
import com.web.notice.dao.NoticeDao;

public class BoardDao {
	//properties 생성
	private final Properties sql=new Properties();
	public BoardDao() {
		String path=NoticeDao.class.getResource("/sql/board/boardsql.properties").getPath();
		try {
			sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//Board 객체 생성하는 메소드
	public Board getBoard(ResultSet rs) throws SQLException{
		return Board.builder().boardNo(rs.getInt("board_no"))
				.boardTitle(rs.getString("board_title"))
				.boardWriter(rs.getString("board_writer"))
				.boardContent(rs.getString("board_content"))
				.boardOriginalFileName(rs.getString("board_original_filename"))
				.boardRenamedFileName(rs.getString("board_renamed_filename"))
				.boardDate(rs.getDate("board_date"))
				.boardReadConut(rs.getInt("board_readcount")).build();
	}

	//컬럼 수
	public int selectBoardCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectBoardCount"));
			//SELECT COUNT(*) FROM BOARD
			rs=pstmt.executeQuery();
			if(rs.next()) count=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return count;
	}

	//게시글 전체 조회
	public List<Board> selectBoardList(Connection conn, int cPage, int numPerpage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Board> boardList=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectBoardList"));
			//SELECT * FROM (SELECT ROWNUM AS RNUM, B.* FROM (SELECT * FROM BOARD)B) WHERE RNUM BETWEEN ? AND ?
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				boardList.add(getBoard(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}

	public Board selectBoardContent(Connection conn, int boardNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Board b=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectBoard"));
			pstmt.setInt(1, boardNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				b=getBoard(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return b;
	}

	public int insertBoard(Connection conn, Board b) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertBoard"));
			//INSERT INTO BOARD VALUES(SEQ_NOTICE_NO.NEXTVAL,?,?,?,?,?,DEFAULT,DEFAULT)
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardWriter());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getBoardOriginalFileName());
			pstmt.setString(5, b.getBoardRenamedFileName());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

}
