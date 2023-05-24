<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>500 Error</title>
</head>
<body>
	<h3 style="color:red">500 Error 발생!</h3>
    <!-- isErrorPage="false"인 경우에 exception객체를 사용할 수 없어서 에러가 발생한다. -->
	<p><%=exception.getMessage() %></p>
</body>
</html>