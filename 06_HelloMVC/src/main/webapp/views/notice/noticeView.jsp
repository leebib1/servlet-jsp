<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.web.notice.dto.Notice, java.util.List" %>
<%@ include file="/views/common/header.jsp"%>
<%
	List<Notice> noticelist=(List)request.getAttribute("noticelist");
	String loginId="";
	if(loginMember!=null){
		loginId=loginMember.getUserId(); 
	}
%>
<section id="notice-container">
	<h2>공지사항</h2>
	<div>
	<% if(loginId.equals("admin")){ %>
		<button onclick="location.href='<%=request.getContextPath() %>/notice/noticewrite.do'">글 작성</button>
	<% }%>
	</div>
	<table id="tbl-notice">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>첨부파일</th>
			<th>작성일</th>
		</tr>
		<!-- 내용출력할것 첨부파일 있으면 이미지, 없으면 공란으로 표시 이미지파일은 web/images/file.png에 저장 -->
		<%
		if(!noticelist.isEmpty()){
			for(Notice n:noticelist){
			%>
			<tr>
				<td><%=n.getNoticeNo() %></td>
				<td onclick="location.href='<%=request.getContextPath() %>/notice/noticeContent.do?noticeNo=<%=n.getNoticeNo() %>'">
				<%=n.getNoticeTitle() %>
				</td>
				<td><%=n.getNoticeWriter() %></td>
				<td>
					<% if(n.getFilepath()!=null){ %>
						<img src="<%=request.getContextPath() %>/images/file.png" width="20">
					<%} %>
				</td>
				<td><%=n.getNoticeDate() %></td>
			</tr>
		<%	}
		}else{ %>
			<tr><td colspan="5">출력될 데이터가 없습니다</td></tr>
		<%}
		%>
	</table>
	<div id="pageBar">
		<%=request.getAttribute("pageBar") %>
	</div>
</section>
<%@ include file="/views/common/footer.jsp"%>


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
	width: 100%;
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
}

table#tbl-notice th, table#tbl-notice td {
	border: 1px solid;
	padding: 5px 0;
	text-align: center;
}

section#notice-container>div:first-of-type{
	display: flex;
	justify-content: right;
}
</style>