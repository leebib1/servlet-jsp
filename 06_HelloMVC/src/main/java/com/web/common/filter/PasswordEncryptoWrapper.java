package com.web.common.filter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class PasswordEncryptoWrapper extends HttpServletRequestWrapper{

	public PasswordEncryptoWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String key) {
		if(key.equals("password")) {
			//암호화처리 로직
//			System.out.println("원본 : "+super.getParameter(key));
			String encryptData=getSHA512(super.getParameter(key));
//			System.out.println("암호화 : "+encryptData);
			return encryptData;
		}
		return super.getParameter(key);
	}
	
	private String getSHA512(String oriVal) {
		//단방향 암호화 처리
		//java에서 제공하는 클래스와 메소드를 이용한다.
		//MessageDigest Class를 이용
		MessageDigest md=null;
		try {
			md=MessageDigest.getInstance("SHA-512");
			
		}catch(NoSuchAlgorithmException e){ //제공하는 알고리즘이 아닐때 발상해는 예외
			e.printStackTrace();
		}
		//생성된 MessageDigest 클래스를 이용해서 원본값을 암호화 처리한다.
		//암호화는 바이트단위로 쪼개서 처리된다.
		byte[] oriValByte=oriVal.getBytes();
		md.update(oriValByte); //바이트단위로 쪼개진 배열을 암호화 함
		byte[] encryptData=md.digest(); //암호화된 데이터를 바이트 타입 배열로 반환
		String encypteStrData=Base64.getEncoder().encodeToString(encryptData); //암호화된 바이트 타입 배열을 String형으로 인코딩해서 바꿔준다.
		
		
		return encypteStrData;
	}
}
