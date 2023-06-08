package com.web.notice.model.service;

import java.sql.Connection;
import java.util.List;

import com.web.notice.dao.NoticeDao;
import com.web.notice.dto.Notice;

import static com.web.common.JDBCTemplate.*;
public class NoticeService {
	private NoticeDao dao=new NoticeDao();
	
	//공지사항 글 전체 조회
	public List<Notice> selectNotices(int cPage, int numPerpage) {
		Connection conn=getConnection();
		List<Notice> noticelist=dao.selectNotices(conn, cPage, numPerpage);
		close(conn);
		return noticelist;
	}

	//공지사항 글 내용 조회
	public Notice selectNoticeContent(int noticeNo) {
		Connection conn=getConnection();
		Notice n=dao.selectNoticeContent(conn, noticeNo);
		close(conn);
		return n;
	}

	//공지사항 게시글 추가
	public int insertNotice(Notice notice) {
		Connection conn=getConnection();
		int result=dao.insertNotice(conn, notice);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	//공지사항 글 갯수
	public int selectNoticeCount() {
		Connection conn=getConnection();
		int count=dao.selectNoticeCount(conn);
		close(conn);
		return count;
	}
	
}
