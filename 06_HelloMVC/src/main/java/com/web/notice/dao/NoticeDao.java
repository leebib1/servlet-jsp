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
	public List<Notice> selectNotices(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Notice> noticelist=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectnotices"));
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
	
	
	
	
	
}
