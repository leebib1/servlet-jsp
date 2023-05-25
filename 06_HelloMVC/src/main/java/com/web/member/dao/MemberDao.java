package com.web.member.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	private Member getMember(ResultSet rs) throws SQLException{
		return Member.builder().userId(rs.getString("userid"))
				.passward(rs.getString("password"))
				.userName(rs.getString("username"))
				.age(rs.getInt("age"))
				.gender(rs.getString("gender").charAt(0))
				.email(rs.getString("email"))
				.phone(rs.getString("phone"))
				.address(rs.getNString("address"))
				.hobby(rs.getString("hobby").split(","))
				.enrollDate(rs.getDate("enrolldate"))
				.build();
	}

}
