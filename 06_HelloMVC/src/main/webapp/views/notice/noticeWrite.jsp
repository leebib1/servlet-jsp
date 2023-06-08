<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<% 
	//loginMember는 header.jsp에 선언되어있음
	String loginId="";
	if(loginMember!=null){
		loginId=loginMember.getUserId(); 
	}
%>
<div id="notice-container">
	<!-- form안에 file형식 데이터를 전송하는 경우 method="post" enctype="multipart/form-data"를 지정해줘야한다. -->
	<form action="<%=request.getContextPath() %>/notice/noticewriteend.do" method="post" enctype="multipart/form-data">
		<table id="tbl-notice">
			<tr>
				<th>제 목</th>
				<td><input type="text" name="notice_title" id="noticeTitle" placeholder="제목을 입력하세요."></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="notice_writer" value="<%=loginId %>" readonly></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><input type="file" name="notice_file" multiple></td>
			</tr>
			<tr>
				<th>내 용</th>
				<td><textarea name="notice_content" cols="50" rows="10"></textarea></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="등록하기" onclick="return fn_titleCheck();">
				</th>
			</tr>
		</table>
	</form>
</div>
<script>
	function fn_titleCheck(){
		const title=$(".noticeTitle").val();
		if(title.length==0){
			title.focus();
			alert("제목은 필수로 입력해야합니다.");
			return false;
		}
	}
</script>
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