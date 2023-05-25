<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.web.member.model.dto.Member" %>
<% 
	Member loginMember=(Member)session.getAttribute("loginMember");
%>
<header>
	<h1>Hello MVC</h1>
	<div class="login-container">
	<% if(loginMember==null||loginMember.getUserId()==null){ %>
		<form id="loginFrm" action="<%=request.getContextPath()%>/login.do">
			<table>
				<tr>
					<td><input type="text" name="userId" id="userId"
						placeholder="아이디"></td>
				</tr>
				<tr>
					<td><input type="password" name="password" id="password"
						placeholder="패스워드"></td>
					<td><input type="submit" value="전송"></td>
				</tr>
				<tr>
					<td><label for="saveId"><input type="checkbox"
							name="saveId" id="saveId">아이디 저장</label> <input type="button"
						value="회원가입"></td>
				</tr>
			</table>
		</form>	
	<%}else{%>
		<table id="logged-in">
			<tr>
				<td colspan="2">
				<%=loginMember.getUserName() %>님, 환영합니다.
				</td>
			</tr>
			<tr>
				<td><input type="button" value="내 정보"></td>
				<td><input type="button" value="로그아웃"></td>
			</tr>
		</table>
	<% }%>
	</div>
	<nav>
		<ul class="main-nav">
			<li class="home"><a href="">Home</a></li>
			<li id="notice"><a href="">공지사항</a></li>
			<li id="board"><a href="">게시판</a></li>
		</ul>
	</nav>
</header>