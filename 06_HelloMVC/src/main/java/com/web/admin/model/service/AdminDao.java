package com.web.admin.model.service;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.web.common.AESEncryptor;
import com.web.member.model.dto.Member;

import static com.web.common.JDBCTemplate.*;
import static com.web.member.dao.MemberDao.getMember;

public class AdminDao {
	private final Properties sql=new Properties();
	{
		String path = AdminDao.class.getResource("/sql/admin/adminsql.properties").getPath();
		try {
			sql.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		try(FileReader fr=new FileReader(path)){
//			sql.load(fr);
//		}
	}

	public List<Member> selectMembers(Connection conn, int cPage, int numPerpage) {
		PreparedStatement pstmt=null;
		List<Member> members=new ArrayList();
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectmember"));
//			SELECT * FROM (SELECT ROWNUM AS RNUM, M.* FROM (SELECT * FROM MEMBER)M) WHERE RNUM BETWEEN ? AND ?
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				members.add(getMember(rs));
			}
			for(Member m:members) {
//				System.out.println(m);
				try {
					m.setEmail(AESEncryptor.decryptData(m.getEmail()));
				}catch(Exception e) {
//					System.out.println("암호화 실패했습니다.");
				}
				try {
					m.setPhone(AESEncryptor.decryptData(m.getPhone()));
				}catch(Exception e) {
//					System.out.println("암호화 실패했습니다.");
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return members;
	}

	public int selectMemberCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectMemberCount"));
			//SELECT COUNT(*) FROM MEMBER
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	

}
