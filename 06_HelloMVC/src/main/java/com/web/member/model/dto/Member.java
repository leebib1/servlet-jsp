package com.web.member.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
	private String userId;
	private String passward;
	private String userName;
	private char gender;
	private int age;
	private String email;
	private String phone;
	private String address;
	private String[] hobby;
	private Date enrollDate;
	
}
