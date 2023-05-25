package com.jsp.model.service;

import java.sql.Connection;
import java.util.List;

import com.jsp.model.dao.MemberDao;
import com.jsp.model.dto.MemberDTO;
import static com.jsp.common.JDBCTemplate.*;

public class MemberService {
	private MemberDao dao=new MemberDao();
	public List<MemberDTO> selectMemberAll(){
		Connection conn=getConnection();
		List<MemberDTO> list=dao.selectMemberAll(conn);
		close(conn);
		return list;
	}
	public List<MemberDTO> searchByName(String name) {
		Connection conn=getConnection();
		List<MemberDTO> list=dao.searchByName(conn, name);
		close(conn);
		return list;
	}
	
	
	
}
