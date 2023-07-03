<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>액션 태그</title>
</head>
<body>
	<h2>액션태그 활용하기</h2>
	<p>jsp페이지에서 java코드를 html태그 방식으로 작성할 수 있게 해주는 태그</p>
	<ui>
		<li>표준 액션 태그 : 기본 jsp에서 제공하는 태그</li>
		<li>커스텀 액션 태그(JSTL) : 별도 jsp에서 제공하는 태그로 jar파일을 추가해야 사용 가능하다.</li>
	</ui>
	<h3>표준 액션 태그 이용</h3>
	<p>태그를 작성할 때 jsp prefix를 사용한다.</p>
	<%-- 예) <jsp:태그명></jsp:태그명> 닫아주지 않으면 에러가 발생하므로 반드시 닫아줘야한다. --%>
	<h3>jsp:include 태그 활용</h3>
	<p>다른 jsp 페이지를 불러와서 출력해주는 태그</p>
	<%-- <%@ include %>태그와 비슷한 기능을 한다. --%>
	<p>jsp:include page="불러올 페이지의 경로"</p>
	<a href="<%=request.getContextPath()%>/views/includeTest.jsp">jsp:include 테스트</a>
	
	<h3>커스텀 액션 태그 : JSTL 이용하기</h3>
	<h4>EL표현식 활용</h4>
	<p>java코드로 생성된 데이터를 페이지에 출력홰주는 표현식</p>
	<p><%-- ${ } --%> : 공용 객체(request, session, Application)에 저장된 데이터를 불러와서 출력해준다.</p>
	<h3>
		<a href="<%=request.getContextPath() %>/views/el/eltest.jsp">
		EL 사용하기
		</a>
	</h3>
</body>
</html>