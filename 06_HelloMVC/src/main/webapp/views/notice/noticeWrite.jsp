<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<div id="notice-container">
	<form action="" method="post">
		<table id="tbl-notice">
			<tr>
				<th>제 목</th>
				<td>반드시 작성되야함.</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>반드시 있어야하고 로그인한 사용자 아이디가 들어가야함. 작성못하게!</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>파일을 입력받아야함</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="등록하기" onclick="">
				</th>
			</tr>
		</table>
	</form>
</div>
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