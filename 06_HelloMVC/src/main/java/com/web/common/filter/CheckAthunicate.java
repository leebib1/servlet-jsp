package com.web.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.web.member.model.dto.Member;

/**
 * Servlet Filter implementation class CheckAthunicate
 */
@WebFilter(servletNames = "memberView")
public class CheckAthunicate extends HttpFilter implements Filter {
       
    
    public CheckAthunicate() {
    }

	
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpSession session=((HttpServletRequest)request).getSession();
		Member loginMember=(Member)session.getAttribute("loginMember");
		if(loginMember==null||!loginMember.getUserId().equals(request.getParameter("userId"))) {
			//로그인이 안 되어있거나 로그인 정보가 맞지 않을 때
			request.setAttribute("msg", "잘못된 접근입니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
//			throw new RuntimeException("잘못된 접근입니다."); 예외를 생성해서 해당 경우에 예외처리할 수 있다.
		}else {		
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
