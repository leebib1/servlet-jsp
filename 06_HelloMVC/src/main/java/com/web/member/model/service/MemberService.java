package com.web.member.model.service;

import static com.web.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.web.member.dao.MemberDao;
import com.web.member.model.dto.Member;

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

	public int updateMember(Member m) {
		Connection conn=getConnection();
		int result=dao.updateMember(conn, m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int updatePassword(String userId, String newPwd) {
		Connection conn=getConnection();
		int result=dao.updatePassword(conn, userId, newPwd);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}


}
