package com.filtertest.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyRequestWrapper extends HttpServletRequestWrapper{

	//HttpServletRequest를 매개변수로 갖는 생성자를 필수로 생성해야한다.
	public MyRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		String oriData=super.getParameter(name); //클라이언트가 보낸 원본값 request.getParameter와 동일
		return oriData+"-bs-"; //모든 원본 데이터 뒤에 -bs-를 붙이게된다.
	}

}
