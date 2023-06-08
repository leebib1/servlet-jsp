package com.web.admin.model.dao;

import static com.web.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.web.admin.model.service.AdminDao;
import com.web.member.model.dto.Member;

public class AdminService {
	private AdminDao dao=new AdminDao();
	public List<Member> selectMembers(int cPage, int numPerpage) {
		Connection conn=getConnection();
		List<Member> members=dao.selectMembers(conn, cPage, numPerpage);
		close(conn);
		return members;
	}
	public int selectMemberCount() {
		Connection conn=getConnection();
		int result=dao.selectMemberCount(conn);
		close(conn);
		return result;
	}
	public List<Member> selectMemberByKeyword(String type, String keyword, int cPage, int numPerpage) {
		Connection conn=getConnection();
		List<Member> members=dao.selectMemberByKeyword(conn, type, keyword, cPage, numPerpage);
		close(conn);
		return members;
	}
	public int selectMemberByKeywordCount(String type, String keyword) {
		Connection conn=getConnection();
		int count=dao.selectMemberByKeywordCount(conn, type, keyword);
		close(conn);
		return count;
	}
}
