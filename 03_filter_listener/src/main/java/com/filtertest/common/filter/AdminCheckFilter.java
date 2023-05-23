package com.filtertest.common.filter;

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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AdminCheckFilter
 */
@WebFilter(servletNames = {"memberManager","encoding"})

public class AdminCheckFilter extends HttpFilter implements Filter {
       
    
    public AdminCheckFilter() {
    }

	
	public void destroy() {
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session=((HttpServletRequest)request).getSession();
		String loginId=(String)session.getAttribute("loginId");
		System.out.println(loginId);
		if(loginId!=null&&loginId.equals("admin")) {
			
			chain.doFilter(request, response);
		}else {
			((HttpServletResponse)response).sendRedirect("/03_filter_listener/");
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
