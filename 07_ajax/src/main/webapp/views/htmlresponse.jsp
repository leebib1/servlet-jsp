<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.ajax.model.vo.Actor" %>
<%
	List<Actor> actors=(List)request.getAttribute("actors");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>htmlResponse</title>
<script src="http://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<body>
<table>
	<tr>
		<th>이름</th>
		<th>전화번호</th>
		<th>프로필</th>
	</tr>
	<%if(actors.isEmpty()){ %>
		<tr>
			<td colspan="3">조회된 배우가 없습니다.</td>
		</tr>
	<%}else{
		for(Actor a:actors){%>
		<tr>
			<td><%=a.getName() %></td>
			<td><%=a.getPhone() %></td>
			<td>
				<img src="<%=request.getContextPath() %>/images/<%=a.getProfile() %>" width="100">
			</td>
		</tr>
	<%	}
	}%>
</table>

</body>
</html>