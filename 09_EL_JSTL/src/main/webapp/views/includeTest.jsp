<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ include file="/views/common/header.jsp" %> --%>
<!-- jsp 액션 태그를 이용해서 헤더 불러오기 -->
<%request.setCharacterEncoding("utf-8"); %>
<jsp:include page="/views/common/header.jsp">
	<jsp:param name="title" value="메인화면"/>
</jsp:include>
<section>
	<h3>본문 내용 출력</h3>
	<%-- <p>접속한 회원 : <%=userId %></p> --%>
</section>
	<jsp:forward page="/views/board.jsp"/>
</body>
</html>