<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.web.member.model.dto.Member" %>
<% Member m=(Member)request.getAttribute("result"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	div#checkId-container{
		text-align: center;
		padding-top:10px;
	}
	span#duplicated{
		color: red;
		font-weight:bolder;
	}
</style>
</head>
<body>
	
	<div id="checkId-container">
		<%if(m==null){ %>
			[<span><%=request.getParameter("userId") %></span>]는 사용가능합니다.	
			<br><br>
			<button type="button" >닫기</button>
		<%}else{ %>
			[<span id="duplicated"><%=m.getUserId() %></span>]는 사용중입니다.
			<br><br>
			<!-- 아이디 재입력창 구성 -->
			<form action="<%=request.getContextPath() %>/member/idduplate.do" method="get">
				<input type="text" name="userId" id="userId">
				<input type="submit" value="중복검사" >
			</form>
		<%} %>
	</div>
	<script type="text/javascript">
		const btn=document.querySelector("button[type=button]");
		btn.addEventListener("click",e=>{
			opener.document.querySelector("#userId_").value="<%=request.getParameter("userId")%>";
			close();
		})
	</script>
</body>
</html>