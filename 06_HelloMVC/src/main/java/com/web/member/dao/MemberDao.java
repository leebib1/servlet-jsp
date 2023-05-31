package com.web.member.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.web.member.model.dto.Member;
import static com.web.member.common.JDBCTemplate.*;

public class MemberDao {
	private final Properties sql=new Properties();
	{
		String path = MemberDao.class.getResource("/sql/member/member_sql.properties").getPath();
		try {
			sql.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public Member selectByUserIdAndPw(Connection conn, String id, String pwd) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectByUserIdAndPw"));
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=getMember(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}
	
	public static Member getMember(ResultSet rs) throws SQLException{
		return Member.builder().userId(rs.getString("userid"))
				.passward(rs.getString("password"))
				.userName(rs.getString("username"))
				.age(rs.getInt("age"))
				.gender(rs.getString("gender").charAt(0))
				.email(rs.getString("email"))
				.phone(rs.getString("phone"))
				.address(rs.getNString("address"))
				.hobby(rs.getString("hobby")!=null?rs.getString("hobby").split(","):null)
				.enrollDate(rs.getDate("enrolldate"))
				.build();
	}

	public int enrollMember(Connection conn, Member m) {
		PreparedStatement pstmt=null;
		int result=0;
		System.out.println(m.toString());
		try {
			pstmt=conn.prepareStatement(sql.getProperty("enrollMember"));
			//INSERT INTO MEMBER VALUES (?,?,?,?,?,?,?,?,?,DEFAULT
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassward());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, String.valueOf(m.getGender()));
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, String.join(",",m.getHobby()));
			result=pstmt.executeUpdate();

		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public Member selectByUserId(Connection conn, String userId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectByUserId"));
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=getMember(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}

	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updateMember"));
			//UPDATE MEMBER SET USERNAME=?,AGE=?,GENDER=?,EMAIL=?,PHONE=?,ADDRESS=?,HOBBY=? WHERE USERID=?
			pstmt.setString(1, m.getUserName());
			pstmt.setInt(2, m.getAge());
			pstmt.setString(3, String.valueOf(m.getGender()));
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getPhone());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, String.join(",", m.getHobby()));
			pstmt.setString(8, m.getUserId());
			result=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int updatePassword(Connection conn, String userId, String newPwd) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updatePwd"));
			//UPDATE MEMBER SET PASSWORD=? WHERE USERID=?
			pstmt.setString(1, newPwd);
			pstmt.setString(2, userId);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	

}
