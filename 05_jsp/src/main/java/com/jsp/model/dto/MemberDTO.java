package com.jsp.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//lombok 라이브러리 활용
//데이터 저장용 클래스를 생성 했을 때 필요한 setter,getter,생성자 등을 자동으로 생성해준다
//1. lombok.jar 파일 다운로드
//2. lombok 설정 ->IDE도구 연동
//3. lombok이 제공하는 어노테이션 활용

@Data //setter, getter, 기본 생성자, toString, equals, hashCode
@AllArgsConstructor //전체 필드에 대한 매개변수 생성자 ->기본 생성자가 사라짐
@NoArgsConstructor //기본 생성자
@Builder //빌더패턴 생성
//@Getter
//@Setter
//@ToString(exclude = {"memberPwd"}) //해당 필드만 제외한다.
//@EqualsAndHashCode(exclude = {"hobby"})
//@RequiredArgsConstructor //NonNull, final로 설정된 필드를 매개변수로 갖는 생성자
public class MemberDTO {
//	@Getter
//	@NonNull //null값을 허용하지 않음
	private String memberId;
//	@Getter @Setter //해당 필드에만 생성 가능
	private String memberPwd;
	private String memberName;
	private int age;
	private String gender;
	private String email;
	private String phone;
	private String address;
	private String hobby;
	private Date enrollDate;
	
	
}
