package com.web.member.model.service;

import java.sql.Connection;

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
}
