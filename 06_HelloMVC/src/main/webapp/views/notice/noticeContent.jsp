<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.web.notice.dto.Notice, java.util.List" %>
<%@ include file="/views/common/header.jsp"%>
<% Notice n=(Notice)request.getAttribute("notice");
	String loginId=((Member)session.getAttribute("loginMember")).getUserId();%>
<div id="notice-container">
	<table id="tbl-notice">
	<%if(n!=null&&n.getNoticeWriter().equals(loginId)){
		%>
		<tr>
			<th>제 목</th>
			<td><%=n.getNoticeTitle() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=n.getNoticeWriter() %></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td><%=n.getFilepath()!=null?n.getFilepath():"" %></td>
		</tr>
		<tr>
			<th>내 용</th>
			<td><%=n.getNoticeContent() %></td>
		</tr>
		<tr>
			<th colspan="2"><input type="button" value="수정하기" onclick="">
				<input type="button" value="삭제하기" onclick=""></th>
		</tr>
		<%}else{%>
		<tr><td colspan="4">조회된 데이터가 없습니다.</td></tr>
		<%}%>
	</table>
</div>
<%@ include file="/views/common/footer.jsp"%>

<!-- 스타일추가 -->

<style>
section#notice-container {
	width: 600px;
	margin: 0 auto;
	text-align: center;
}

section#notice-container h2 {
	margin: 10px 0;
}

table#tbl-notice {
	width: 500px;
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
	clear: both;
}

table#tbl-notice th {
	width: 125px;
	border: 1px solid;
	padding: 5px 0;
	text-align: center;
}

table#tbl-notice td {
	border: 1px solid;
	padding: 5px 0 5px 10px;
	text-align: left;
}
</style>