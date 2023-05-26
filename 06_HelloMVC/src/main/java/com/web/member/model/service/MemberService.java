package com.web.member.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.web.member.dao.MemberDao;
import com.web.member.model.dto.Member;

import static com.web.member.common.JDBCTemplate.*;

public class MemberService {
	private MemberDao dao=new MemberDao();
	public Member selectByUserIdAndPw(String id, String pwd) {
		Connection conn=getConnection();
		Member m=dao.selectByUserIdAndPw(conn, id, pwd);
		close(conn);
		return m;
	}
	
	public int enrollMember(Member m){
		Connection conn=getConnection();
		int result=dao.enrollMember(conn, m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public Member selectByUserId(String userId) {
		Connection conn=getConnection();
		Member m=dao.selectByUserId(conn, userId);
		close(conn);
		return m;
	}
}