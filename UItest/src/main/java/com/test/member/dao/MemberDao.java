package com.test.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.test.member.dto.Member;
import static com.test.common.JDBCTemplate.*;

public class MemberDao {

	public int insertMember(Connection conn, Member m) {
		PreparedStatement pstmt=null;
		int result=0;
//		private String userId;
//		private String password;
//		private String userName;
//		private int age;
//		private String email;
//		private String phone;
//		private String address;
//		private String gender;
//		private String[] hobby;
		String sql="INSERT INTO MEMBER VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getUserName());
			pstmt.setInt(4, m.getAge());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getPhone());
			pstmt.setString(7, m.getAddress());
			pstmt.setString(8, m.getGender());
			pstmt.setString(9, String.join(",", m.getHobby()));
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

}
