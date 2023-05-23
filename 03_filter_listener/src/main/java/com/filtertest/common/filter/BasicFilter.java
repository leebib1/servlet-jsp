package com.filtertest.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class BasicFilter implements Filter{

	//doFilter만 추상메소드이기 때문에 doFilter만 구현해도 된다. 나머지 두개는 디폴트메소드
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("BasicFilter 실행함");
		//<url-pattern>/*</url-pattern> 지정해둔 경우 모든 요청에서 필터를 실행하고 다음 로직을 실행하지 않는다.
		
		//Wrapper클래스 적용
		MyRequestWrapper mrw=new MyRequestWrapper((HttpServletRequest)request);
		
		//다음 로직이 실행될 수 있게 지정
//		chain.doFilter(request, response);
		chain.doFilter(mrw, response); //메소드 재정의한 wrapper 객체를 넘김
	}

}
