<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL을 적용하기 위해서는 반드시 페이지에 지시자로 taglib를 선언해야한다. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3><c:out value="core tag 출력!!"/></h3>
	
	<h2>set/out 태그 이용하기</h2>
	<p>
		c:out태그 : value 속성에 있는 값을 페이지에 출력할 때 사용하는 태그<br>
		c:set태그 : 내장 객체 영역에 데이터를 key:value 형식으로 저장할 때 사용하는 태그
	</p>
	<ul>c:set 태그 속성
		<li>var : key값(변수명)</li>
		<li>value : key(변수)에 저장될 값. EL표현식, 리터럴을 많이 사용한다.</li>
		<li>scope : 변수가 선언될 내장 객체를 지정한다. request, session, application</li>
	</ul>
	<ul>c:out 태그 속성
		<li>value : 출력될 데이터. EL표현식, 리터럴을 많이 사용한다.</li>
		<li>default : 출력될 데이터가 없으면 대체로 출력시킬 값</li>
		<li>escapeXml : value속성에 태그 형식으로 작성했을 때 태그로 해석할지 여부를 선택</li>
	</ul>
	
	<h3>변수 선언하기</h3>
	<c:set var="comment" value="점심 맛있게 드셨나요?"/>
	<p>${comment }</p>
	<c:set var="path" value="${pageContext.request.contextPath }"/>
	<p>절대 경로 : ${path }</p>
	<p>scope 속성을 사용하면 원하는 객체 영역을 지정해서 데이터를 저장할 수 있다.</p>
	<c:set var="test" value="requestData" scope="request"/>
	<c:set var="test" value="sessionData" scope="session"/>
	<c:set var="test" value="applicationData" scope="application"/>
	<p>${test }</p>
	<p>${sessionScope.test }</p>
	<p>${applicationScope.test }</p>
	
	<h3>c:out 태그를 이용해서 데이터 출력</h3>
	<p><c:out value="점심은 뭐 드셨나요?"/></p>
	<p><c:out value="${path }"/></p>
	<p>${path }</p>
	<c:set value="<script>location.assign('http://www.naver.com');</script>" var="testData"/>
	<%-- <div>${testData }</div> --%>
	<div><c:out value="${testData }"/> : c:out은 스크립트로 인식하지 않아서 바로 실행하지 않음. 보안상 사용한다.</div>
	<div><c:out value="${testData }" escapeXml="true"/></div>
	
	<p>${test11 }</p>
	<c:set value="데이터!" var="test11"/>
	<p><c:out value="${test11 }" default="데이터 없음"/></p>
	
	
	<h3>c:url 태그</h3>
	<p>링크가 될 주소값 데이터를 저장하는 태그. c:set과 유사하다.</p>
	<c:url var="cesearch" value="http://search.naver.com/search.naver">
		<c:param name="query" value="김찬은"/>
	</c:url>
	<a href="${cesearch }">검색</a>
	
</body>
</html>