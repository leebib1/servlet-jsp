package com.test.member.dto;

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
	private String password;
	private String userName;
	private int age;
	private String email;
	private String phone;
	private String address;
	private String gender;
	private String[] hobby;
	
}
