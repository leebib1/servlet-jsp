<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board</title>

</head>
<body>
	<%-- <p><%=headerData%></p> 
		include 되기 전에는 해당 데이터를 찾을 수 없음. --%>
	<%@ include file="/views/common/header.jsp"%>
	<section>
		<h2>본문 내용</h2>
		<p><%=headerData%></p>
		<table>
			<tr>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			<tr>
				<td>제목1</td>
				<td>작성자1</td>
				<td>작성일1</td>
			</tr>
		</table>
	</section>
	<%@ include file="/views/common/footer.jsp"%>
</body>
</html>