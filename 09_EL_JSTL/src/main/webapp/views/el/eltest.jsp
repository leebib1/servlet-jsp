<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.el.model.vo.Snack, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 표현식 활용</title>
</head>
<body>
	<h3>EL표현식 활용하기</h3>
	<p>
		EL <%-- ${ } --%>로 공용 객체에 저장된 데이터를 페이지에 출력해준다.<br>
		servlet에서 request, session에 setAttribute() 메소드로 저장한 데이터를 자동으로 탐색해서 key값을 기준으로 데이터를 가져온다.<br>
		getter메소드를 호출하지 않고 필드명을 작성하면 EL이 자동으로 getter를 호출해서 데이터를 출력해준다.<br>
		형변환을 하지 않아도 자동으로 맞춰준다.
	</p>
	<h2>리터럴값 출력하기</h2>
	<p><%="오랜만에 수업 너무 재밌다" %></p>
	<p>${"우와! 신기하다" }</p>
	<p>나이는 ${19}</p>
	
	<h2>내장 객체(공용 객체)에 저장된 데이터 출력하기</h2>
	<p>request, session, application(ServletContext)에 저장된 데이터를 출력</p>
	<%
		request.setAttribute("name", "유병승");
		session.setAttribute("age",19);
		application.setAttribute("test","기본 데이터");
		String email="yoo@yoo.com"; //내장객체에 저장되지 않은 값은 탐색하지 못한다.
		request.setAttribute("email",email);
	%>
	<h3>${name}</h3>
	<h3><%=request.getAttribute("name") %></h3>
	<h3>${age }</h3>
	<h3>${test }</h3>
	<h3>${email }</h3>
	
	<h2>EL표현식에서 연산자 활용하기</h2>
	<%
		request.setAttribute("su",19);
		request.setAttribute("su2",30);
		request.setAttribute("su3",30);
		request.setAttribute("testData","admin");
	%>
	<h3>산술 연산 처리하기</h3>
	<h4>+연산 : ${su+su2 }</h4>
	<h4>-연산 : ${su-su2 }</h4>
	<h4>*연산 : ${su*su2 }</h4>
	<h4>/연산 : ${su/su2 }</h4>
	<h4>%연산 : ${su%su2 }</h4>
	<h4>복합 산술 연산 : ${(su%su2*2)/(3+100)*su3 }</h4>
	
	<h3>비교 연산 처리하기</h3>
	<h4>대소 비교</h4>
	<p>19&lt;30 : ${su<su2 } ${su lt su2 }</p>
	<p>&gt; : ${su>su2 } ${su gt su2 }</p>
	<p>&le; : ${su<=su2 } ${su le su2 }</p>
	<p>&ge; : ${su>=su2 } ${su ge su2 }</p>
	<p>&ge; : ${su3>=su2 } ${su3 ge su2 }</p>
	<p>범위 논리연산 : ${su<20&&20<su2}</p>
	
	<h4>동등 비교</h4>
	<p>== : ${su==su2 } ${su3 eq su2 }</p>
	<p>== : ${testData="admin" } ${testData eq "admin" }</p>
	<p>!= : ${su!=su2 } ${su ne su2 }</p>
	<p>!= : ${testData != "userId" } ${testData ne "userId" }</p>	
	<p>논리연산 : ${testData=="admin"&&su2>19 }</p>
	
	<h4>null값 확인하기</h4>
	<p>== : ${testb==null } ${testData==null }</p>
	
	<h4>삼항연산자 활용하기</h4>
	<p>${su>10?"10보다 큼":"10보다 작음" }</p>
	<input type="checkbox" ${su>10?"checked":"" }>check
	
	<h4>메소드 호출</h4>
	<p>${testData.length() }</p>
	<p>${testData.charAt(0) }</p>
	<p>${testData.contains("a")?"있음":"없음" }</p>
	
	<h3>저장된 객체 탐색하기</h3>
	<p>request, session, application에 저장된 객체의 데이터 가져오기</p>
	<%
		Snack s=Snack.builder().type("초콜렛").name("m&m").price(1000).weight(50).build();
		Snack s2=Snack.builder().type("사탕").name("츄파츕스").price(300).weight(10).build();
		Snack s3=Snack.builder().type("젤리").name("하리보").price(2000).weight(60).build();
		request.setAttribute("s",s);
		request.setAttribute("s2",s2);
		request.setAttribute("s3",s3);
		request.setAttribute("snacks",List.of(s,s2,s3));
		request.setAttribute("map",Map.of("s",s,"s2",s2));
	%>
	
	<p>저장된 객체는 key값으로 불러와서 처리 가능하다. 필드의 값을 불러올 때는 getter를 호출하지 않고 필드명으로 불러올 수 있다.</p>
	<p>저장된 s 불러오기 ${s }</p>
	<p>저장된 s의 필드 값 불러오기 ${s.type } ${s.name } ${s.price }</p>
	<p>저장된 s2의 필드 값 불러오기 ${s2.type } ${s2.name } ${s2.price+200 }</p>
	
	<h3>collection으로 저장된 객체 출력하기</h3>
	<p>${snacks }</p>
	<p>${snacks.get(0).type} ${snacks.get(0).name}</p>
	<p>${snacks.get(1).type} ${snacks.get(1).name}</p>
	<p>${map } ${map.s }</p>
	<p>${map.s.price } ${map.s2.price }</p>
	
	<h3>collection의 빈 값 확인하기</h3>
	<p>${snacks.isEmpty() }</p>
	<p>${empty snacks} ${not empty snacks}</p>
	<p>${snacks.size()>0 }</p>
	
	<h4>논리 연산</h4>
	<p>&&, ||, and, or</p>
	<p>${snacks.get(0).type eq "초콜렛" and snacks.get(0).price>800 }</p>
	<p>${snacks.get(0).type eq "초콜렛" or snacks.get(0).price>800 }</p>
	
	<h3>servlet과 연동해서 data처리하기</h3>
	<h4>
		<a href="${pageContext.request.contextPath }/dataTest.do">데이터 처리 실습</a>
	</h4>
	
	<h3>파라미터 값을 EL로 출력하기</h3>
	<form action="${pageContext.request.contextPath }/dataTest.do">
		<input type="text" name="userId">
		<input type="password" name="password">
		<input type="checkbox" name="hobby" value="운동">운동
		<input type="checkbox" name="hobby" value="독서">독서
		<input type="checkbox" name="hobby" value="코딩">코딩
		<input type="checkbox" name="hobby" value="영화">영화
		<input type="checkbox" name="hobby" value="등산">등산
		<input type="submit" value="제출">
	</form>
	
	
	
	
	
</body>
</html>