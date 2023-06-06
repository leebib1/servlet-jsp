package com.web.notice.model.service;

import java.sql.Connection;
import java.util.List;

import com.web.notice.dao.NoticeDao;
import com.web.notice.dto.Notice;

import static com.web.common.JDBCTemplate.*;
public class NoticeService {
	private NoticeDao dao=new NoticeDao();

	public List<Notice> selectNotices() {
		Connection conn=getConnection();
		List<Notice> noticelist=dao.selectNotices(conn);
		close(conn);
		return noticelist;
	}

	public Notice selectNoticeContent(int noticeNo) {
		Connection conn=getConnection();
		Notice n=dao.selectNoticeContent(conn, noticeNo);
		close(conn);
		return n;
	}

	public int insertNotice(Notice notice) {
		Connection conn=getConnection();
		int result=dao.insertNotice(conn, notice);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
}
