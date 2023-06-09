<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<% 
	/* 로그인 시에만 작성할수 있기 때문에 null 값이 들어오는 경우 분기처리 안 함 */
	String loginId="";
	if(loginMember!=null){
		loginId=loginMember.getUserId(); 
	}
%>
<style>
div#board-container {
	width: 600px;
	margin: 0 auto;
	text-align: center;
}

div#board-container h2 {
	margin: 10px 0;
}

table#tbl-board {
	width: 500px;
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
}

table#tbl-board th {
	width: 125px;
	border: 1px solid;
	padding: 5px 0;
	text-align: center;
}

table#tbl-board td {
	border: 1px solid;
	padding: 5px 0 5px 10px;
	text-align: left;
}
</style>

<div id='board-container'>
	<h2>게시판 작성</h2>
	<form action='<%=request.getContextPath()%>/board/boardwriteEnd' method="post" enctype="multipart/form-data">
		<table id='tbl-board'>
			<tr>
				<th>제목</th>
				<td><input type="text" name="board_title" id="boardTitle"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="board_writer" value="<%=loginId %>" readonly></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><input type="file" name="board_file" multiple></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="board_content" cols="50" rows="10"></textarea></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="등록하기" onclick="return fn_titleCheck();"></th>
			</tr>
		</table>
	</form>
</div>
<script>
	function fn_titleCheck(){
		const title=$(".boardTitle").val();
		if(title.length==0){
			title.focus();
			alert("제목은 필수로 입력해야합니다.");
			return false;
		}
	}
</script>

<%@ include file="/views/common/footer.jsp"%>
</body>
</html>