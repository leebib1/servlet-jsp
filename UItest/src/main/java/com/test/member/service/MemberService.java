package com.test.member.service;

import com.test.member.dao.MemberDao;
import com.test.member.dto.Member;
import static com.test.common.JDBCTemplate.*;

import java.sql.Connection;

public class MemberService {
	private MemberDao dao=new MemberDao();
	public int insertMember(Member m) {
		Connection conn=getConnection();
		int result=dao.insertMember(conn, m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

}
