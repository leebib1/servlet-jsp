<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>서버에서 전송한 데이터 출력하기</h2>
	<h4>${snacks.get(0).type } ${snacks.get(0).name }</h4>
	<h4>${snacks.get(1).type } ${snacks.get(1).name }</h4>
	<h4>${snacks.get(2).type } ${snacks.get(2).name }</h4>
	<h4>${snacks.get(3).type } ${snacks.get(3).name }</h4>
	
	<h2>내장 객체에 중복된 key값으로 저장된 데이터 가져오기</h2>
	<p>El에서 내장 객체의 데이터를 가져올 때 작은 범위부터 큰 범위로 탐색을 실행한다.</p>
	<p>request > session > servletContext</p>
	<p>해당하는 데이터를 찾았으면 값을 반환하고 종료하고 못 찾은 경우 큰 범위로 넘어간다.</p>
	<h3>${snack }</h3>
	<h3>중복된 키값이 있을 때 특정 영역에서 찾고 싶으면 EL에서 제공하는 내장 객체를 이용한다.</h3>
	<p>
		requestScope : request영역에서만 key를 탐색한다.<br>
		sessionScope : session영역에서만 key를 탐색한다.<br>
		applicationScope : ServletContext영역에서만 key를 탐색한다.
	</p>
	<h4>${requestScope.snack }</h4>
	<h4>${sessionScope.snack }</h4>
	<h4>${applicationScope.snack }</h4>
	<h4>${requestScope.snacks.get(0).name }</h4>
	
	
	<h2>파라미터 값 출력하기</h2>
	<p>별도의 EL 객체를 이용해서 출력한다. param 이용</p>
	<h3>${param.userId } ${param.password }</h3>
	<p>파라미터가 다수 값인 경우 paramValues를 이용한다.</p>
	<h3>${paramValues.hobby[0] }</h3>
	<h3>${paramValues.hobby[1] }</h3>
	<h3>${paramValues.hobby }</h3>
	
</body>
</html>