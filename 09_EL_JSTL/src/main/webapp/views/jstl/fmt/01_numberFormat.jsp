<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>페이지에 숫자 표현</h2>
	<c:set var="numtest1" value="123456012"/>
	<c:set var="numtest2" value="199883000"/>
	<c:set var="numtest3" value="1"/>
	<c:set var="numtest4" value="1234.567"/>
	<p>일반 숫자 출력 : ${numtest1 }</p>
	<p>기본 fmt 태그를 이용해서 출력 : <fmt:formatNumber value="${numtest1 }"/>원</p>
	<p>숫자 단위 쉼표를 처리하는 속성 : groupingUsed="true||false"</p>
	<p>true : <fmt:formatNumber value="${numtest1 }" groupingUsed="true"/></p>
	<p>false : <fmt:formatNumber value="${numtest1 }" groupingUsed="false"/></p>
	
	<h3>숫자를 화폐로 표시</h3>
	<p>type 속성을 currency로 설정한다.</p>
	<p>원화로 표시 : <fmt:formatNumber value="${numtest2 }" type="currency"/></p>
	<p>원하는 화폐기호로 표시 : <fmt:formatNumber value="${numtest2 }" type="currency" currencySymbol="^.~"/></p>
	<fmt:setLocale value="de_DE"/>
	<p>독일 : <fmt:formatNumber value="${numtest2 }" type="currency"/></p>
	<fmt:setLocale value="ja_JP"/>
	<p>일본 : <fmt:formatNumber value="${numtest2 }" type="currency"/></p>
	
	<h3>퍼센트 표시</h3>
	<p>소수점으로 표시 : 1->100%, 0->0%</p>
	<p>퍼센트 : <fmt:formatNumber value="${numtest3 }" type="percent"/></p>
	<p>퍼센트 : <fmt:formatNumber value="0.25" type="percent"/></p>
	
	<h3>패턴으로 숫자를 표시</h3>
	<p>
		자리수에 맞춰서 특정 문구를 출력<br>
		0 : 지정한 자리에 숫자가 없으면 0으로 표시한다.<br>
		# : 지정한 자리에 숫자가 없으면 표시를 생략한다.
	</p>
	<p>0: ${numtest4 } -> <fmt:formatNumber value="${numtest4 }" pattern="000,000,000"/></p>
	<p>#: ${numtest4 } -> <fmt:formatNumber value="${numtest4 }" pattern="###,###,###"/></p>
	<p>소수점: ${numtest4 } -> <fmt:formatNumber value="${numtest4 }" pattern="000,000.0000"/></p>
	<p>소수점: ${numtest4 } -> <fmt:formatNumber value="${numtest4 }" pattern="###,###.####"/></p>
	
	<h3>소수점 자리수 설정</h3>
	<p>
		minFractionDigits : 최소 소수점 자리<br>
		maxFractionDigits : 최대 소수점 자리
	</p>
	<h3><fmt:formatNumber value="123.1" minFractionDigits="2"/> <fmt:formatNumber value="123.123" minFractionDigits="2"/></h3>
	<h3><fmt:formatNumber value="123.1" maxFractionDigits="2"/> <fmt:formatNumber value="123.127" maxFractionDigits="2"/></h3>
	
	
	
	
	
	
	
</body>
</html>