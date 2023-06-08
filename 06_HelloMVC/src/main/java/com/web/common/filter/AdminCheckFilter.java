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
 * Servlet Filter implementation class AdminCheckFilter
 */
@WebFilter(urlPatterns = {"/admin/*","/notice/noticewrite.do"})
public class AdminCheckFilter extends HttpFilter implements Filter {
       
    
    public AdminCheckFilter() {
        super();
    }

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		//관리자를 체크하는 필터
		HttpServletRequest req=(HttpServletRequest)request;
		HttpSession session=((HttpServletRequest)request).getSession();
		Member loginMember=(Member)session.getAttribute("loginMember");
		if(loginMember==null||!loginMember.getUserId().equals("admin")) {
			req.setAttribute("msg", "잘못된 접근입니다.");
			String prevPage=req.getHeader("Referer");
			System.out.println(prevPage);
			req.setAttribute("loc", "/");
			req.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
