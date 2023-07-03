<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.el.model.vo.Snack" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제어문 활용하기</title>
</head>
<body>
	<h2>JSTL의 제어문 태그 활용하기</h2>
	<p>조건문(if, choose), 반복문(forEach)</p>
	<h3>c:if 태그 이용하기</h3>
	<p>자바에서 if문을 사용하는 것과 동일하다.</p>
	<p>
		c:if<br>
		test : 조건식을 작성하는 속성으로 true, false를 갖는다. 대부분 EL표현식으로 작성한다.
		var : 조건식 결과를 저장하는 변수
	</p>
	<c:set var="su" value="5"/>
	<c:set var="su2" value="20"/>
	<c:if test="${su<su2 }">
		<p><c:out value="${su }"/>는 <c:out value="${su2 }"/>보다 작다.</p>
		<c:if test="${su<15 }">
			<p>우와아</p>
		</c:if>
	</c:if>
	
	<%
		List<String> names=List.of("유병승","조장흠","김찬은","허성현","홍승우");
		request.setAttribute("names",names);
	%>
	<c:if test="${not empty names }">
		<p>이름이 저장되어있습니다.</p>
	</c:if>
	<c:if test="${empty names }">
		<p>저장된 이름이 없습니다.</p>
	</c:if>
	<c:if test="${not empty names }" var="result">
	
	</c:if>
	<p><c:if test="${result }">
		<h1>우하아ㅏ아</h1>
	</c:if></p>
	
	<h3>c:choose 태그 이용하기</h3>
	<p>java에서 switch문을 이용하는 것과 비슷하다.</p>
	<c:choose>
		<c:when test="${su>10 }">
			<p>10보다 크다.</p>
		</c:when>
		<c:when test="${su>5 }">
			<p>5보다 크다.</p>
		</c:when>
		<c:otherwise>
			<p>숫자다.</p>
		</c:otherwise>
	</c:choose>
	
	<h3>반복문 사용하기</h3>
	<p>c:forEach 태그를 이용한다.</p>
	<p>
		1. 기본 반복문 활용 : i를 순차적으로 출력하는 방법
		2. 리스트, 배열에 저장된 데이터를 출력(foreach와 동일)
	</p>
	<ul>c:forEach 태그
		<li>1.begin : 시작번호</li>
		<li>1.end : 끝번호</li>
		<li>1.step : 간격</li>
		<li>1.2.var : 변경되는 값을 저장하는 변수</li>
		<li>2.items : 배열, 리스트</li>
		<li>2.varStatus : 반복문의 정보를 가지고 있는 객체(index, 반복횟수, 시작, 끝 등의 정보를 제공)</li>
	</ul>
	
	<h4>기본 반복문 실행</h4>
	<p>1~10까지 출력하기</p>
	<div>
		<c:forEach begin="1" end="10" step="1" var="i">
			<p><c:out value="${i }"/></p>
		</c:forEach>
		<c:forEach begin="1" end="6" step="1" var="i">
			<h${i }>하하하하하</h${i }>
		</c:forEach>
	</div>
	
	<h2>리스트, 배열에 저장된 데이터 반복하기</h2>
	<div>${names }</div>
	<ul>
		<c:forEach var="name" items="${names }" varStatus="vs">
			<li>${vs.first} ${vs.last} ${vs.index} ${vs.count} ${name }</li>
		</c:forEach>
	</ul>
	
	<%
		List<Snack> snack=List.of(
				Snack.builder().type("초콜릿").name("투유").price(1200).weight(200).build(),
				Snack.builder().type("젤리").name("마이구미").price(1300).weight(60).build(),
				Snack.builder().type("아이스크림").name("뿅따").price(1500).weight(300).build(),
				Snack.builder().type("사탕").name("이클립스").price(2500).weight(30).build(),
				Snack.builder().type("과자").name("맛동산").price(2400).weight(100).build()
				);
		request.setAttribute("snacks",snack);
	%>
	<div>
		<h3>과자 보유 현황</h3>
		<table>
			<tr>
				<th>종류</th>
				<th>이름</th>
				<th>가격</th>
				<th>무게</th>
				<th>재고</th>
			</tr>
			<c:if test="${empty snacks }">
				<tr><td colspan="5">보유한 과자가 없습니다.</td></tr>
			</c:if>
			<c:if test="${not empty snacks }">
				<c:forEach var="s" items="${snacks }" varStatus="vs">
					<tr style="background-color:${vs.first||vs.last?'olive':'lightgray'}">
						<td><c:out value="${s.type }"/></td>
						<td><c:out value="${s.name }"/></td>
						<td><c:out value="${s.price }"/></td>
						<td><c:out value="${s.weight }"/></td>
						<td><c:out value="${s.price/vs.count }"/></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	</div>
	
	<h3>c:forTokens 태그</h3>
	<ul>
		<li>var : 분할된 값이 저장되는 변수</li>
		<li>items : 분할할 대상이 되는 문자열</li>
		<li>delims : 기준이 되는 기호</li>
	</ul>
	<c:forTokens var="hobby" items="운동,여행,코딩,게임" delims=",">
		<p>${hobby }</p>
	</c:forTokens>
	
	<h4>그외 코어 태그 활용하기</h4>
	<h4>c:import 태그</h4>
	<p>다른 페이지를 불러와서 내용을 변수에 저장하는 태그</p>
	<c:import url="/views/common/header.jsp" var="header1">
		<c:param name="title" value="import"/>
	</c:import>
	<div>
		${header1 }
	</div>
	
	<h4>c:catch 태그</h4>
	<%
		String name=null;
	%>
	<c:catch var="e">
		<%=name.length() %>
	</c:catch>
	<c:out value="${e }"/>
	
	
	
</body>
</html>