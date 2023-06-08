package com.web.notice.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.web.admin.model.service.AdminDao;
import com.web.notice.dto.Notice;

import static com.web.common.JDBCTemplate.*;

public class NoticeDao {
	//프로퍼티스 생성
	private final Properties sql=new Properties();
	public NoticeDao() {
		String path = AdminDao.class.getResource("/sql/notice/noticesql.properties").getPath();
		try {
			sql.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Notice getNotice(ResultSet rs) throws SQLException{
		return Notice.builder().noticeNo(rs.getInt("notice_no"))
				.noticeTitle(rs.getString("notice_title"))
				.noticeWriter(rs.getString("notice_writer"))
				.noticeContent(rs.getString("notice_content"))
				.filepath(rs.getString("filepath"))
				.noticeDate(rs.getDate("notice_date"))
				.build();
	}
	
	//공지사항 데이터 전체를 조회하는 메소드
	public List<Notice> selectNotices(Connection conn, int cPage, int numPerpage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Notice> noticelist=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectnotices"));
			//SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM (SELECT * FROM NOTICE)N) WHERE RNUM BETWEEN ? AND ?
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				noticelist.add(getNotice(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return noticelist;
	}
	
	//게시글 본문 조회
	public Notice selectNoticeContent(Connection conn, int noticeNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Notice n=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectNoticeContent"));
			pstmt.setInt(1, noticeNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				n=getNotice(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return n;
	}

	//공지사항 게시글 작성
	public int insertNotice(Connection conn, Notice notice) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertNotice"));
			//INSERT INTO NOTICE VALUES(SEQ_NOTICE_NO.NEXTVAL,?,?,?,DEFAULT,NULL,DEFAULT)
			pstmt.setString(1, notice.getNoticeTitle());
			pstmt.setString(2, notice.getNoticeWriter());
			pstmt.setString(3, notice.getNoticeContent());
			pstmt.setString(4, notice.getFilepath());
			result=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int selectNoticeCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectNoticeCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return count;
	}
	
	
	
	
	
}
