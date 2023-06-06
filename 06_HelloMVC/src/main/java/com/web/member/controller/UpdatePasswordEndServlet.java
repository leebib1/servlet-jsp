package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.member.model.dto.Member;
import com.web.member.model.service.MemberService;

/**
 * Servlet implementation class UpdatePasswordEndServlet
 */
@WebServlet(name="updatePwd",urlPatterns = "/updatePasswordEnd")
public class UpdatePasswordEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdatePasswordEndServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oriPwd=request.getParameter("password");
		String newPwd=request.getParameter("password_new");
		String userId=request.getParameter("userId");
		Member m=new MemberService().selectByUserIdAndPw(userId,oriPwd);
		String msg="",loc="/member/updatePassword.do?userId="+userId;
		if(m==null) {
			//일치하는 값이 없는 경우
			msg="비밀번호가 일치하지 않습니다.";
		}else {
			//일치하는 경우
			int result=new MemberService().updatePassword(userId, newPwd);
			if(result>0) {
				msg="비밀번호가 수정되었습니다.";
				loc="/";
				request.setAttribute("script", "opener.location.replace('"+request.getContextPath()+"/logout.do'); close();");
			}else {
				msg="비밀번호 수정 실패하였습니다.";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
