<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello MVC</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<script src="<%=request.getContextPath()%>/js/jquery-3.7.0.min.js"></script>
</head>
<body>
	<div id="container">
		<%@ include file="/views/common/header.jsp" %>
		<section id="content">
			<h2 align="center" style="margin-top:200px">안녕하세요, MVC입니다.</h2>
		</section>
		<%@ include file="/views/common/footer.jsp" %>
	</div>
</body>
</html>