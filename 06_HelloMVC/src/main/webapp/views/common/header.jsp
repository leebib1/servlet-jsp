<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.web.member.model.dto.Member" %>
<% 
	Member loginMember=(Member)session.getAttribute("loginMember");
	Cookie[] cookies=request.getCookies();
	String saveId=null;
	if(cookies!=null){
		for(Cookie c:cookies){
			if(c.getName().equals("saveId")){
				saveId=c.getValue();
				break;
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello MVC</title>

</head>
<body>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<script src="<%=request.getContextPath()%>/js/jquery-3.7.0.min.js"></script>
<header>
	<h1>Hello MVC</h1>
	<div class="login-container">
	<% if(loginMember==null||loginMember.getUserId()==null){ %>
		<form id="loginFrm" action="<%=request.getContextPath()%>/login.do" onsubmit="return fn_validation();">
			<table>
				<tr>
					<td><input type="text" name="userId" id="userId"
						placeholder="아이디" value="<%=saveId!=null?saveId:"" %>"></td>
				</tr>
				<tr>
					<td><input type="password" name="password" id="password"
						placeholder="패스워드" ></td>
					<td><input type="submit" value="전송"></td>
				</tr>
				<tr>
					<td>
						<label for="saveId"><input type="checkbox" name="saveId" id="saveId"
						 <%=saveId!=null?"checked":"" %>>아이디 저장</label>
						<input type="button" value="회원가입" 
							onclick="location.replace('<%=request.getContextPath()%>/member/enrollMember.do')">
					</td>
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
				<td><input type="button" value="내 정보" 
					onclick="location.assign('<%=request.getContextPath()%>/member/memberView.do?userId=<%=loginMember.getUserId()%>')"></td>
				<td>
					<%-- <form action="<%=request.getContextPath()%>/logout.do"> --%>
						<input type="button" value="로그아웃" onclick="location.replace('<%=request.getContextPath()%>/logout.do')">
					<!-- </form> -->
				</td>
			</tr>
		</table>
	<% }%>
	</div>
	<nav>
		<ul class="main-nav">
			<li class="home"><a href="">Home</a></li>
			<li id="notice"><a href="<%=request.getContextPath()%>/notice/noticeview.do">공지사항</a></li>
			<li id="board"><a href="<%=request.getContextPath()%>/board/boardList.do">게시판</a></li>
			<% if(loginMember!=null&&loginMember.getUserId().equals("admin")){ %>
			<li id="member"><a href="<%=request.getContextPath()%>/admin/memberList.do">회원관리</a></li>
			<%} %>
		</ul>
	</nav>
</header>
<script>
	const fn_validation=()=>{
		const userId=$("#userId").val();
		if(userId.length<4){
			alert("아이디는 4글자 이상입니다.");
			$("#uesrId").val("");
			$("#userId").focus();
			return false;
		}
		/* if($("#password").length<8){
			alert("비밀번호를 입력하세요.");
			return false;
		} */
	}
</script>