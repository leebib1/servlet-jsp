<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.web.notice.dto.Notice, java.util.List" %>
<%@ include file="/views/common/header.jsp"%>
<% Notice n=(Notice)request.getAttribute("notice");
	Member m=(Member)session.getAttribute("loginMember");
	String loginId="";
	if(m!=null){
		loginId=m.getUserId();
	}
	%>
<div id="notice-container">
	<h2>공지사항 상세화면</h2>
	<table id="tbl-notice">
	<%if(n!=null&&loginId!=null){
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
			<td>
				<% if(n.getFilepath()!=null){ %>
					<div class="download_container" onclick="fileDownload('<%=n.getFilepath() %>');">
						<img src="<%=request.getContextPath() %>/images/file.png" width="20">
						<%=n.getFilepath() %>
					</div>
				<%} %>
			</td>
		</tr>
		<tr>
			<th>내 용</th>
			<td><%=n.getNoticeContent() %></td>
		</tr>
			<% if(n.getNoticeWriter().equals(loginId)){ %>
			<tr>
				<th colspan="2"><input type="button" value="수정하기" onclick="">
					<input type="button" value="삭제하기" onclick=""></th>
			</tr>
			<%}%>
		<%}else{%>
		<tr><td colspan="4">조회된 데이터가 없습니다.</td></tr>
		<%}%>
	</table>
</div>
<%@ include file="/views/common/footer.jsp"%>
<script>
	const fileDownload=(filename)=>{
		/* alert("파일 다운로드"); */
		location.assign("<%=request.getContextPath()%>/fileDownload.do?filename="+filename);
	}
</script>
<!-- 스타일추가 -->

<style>
div.download_container{
	cursor: pointer;
}
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