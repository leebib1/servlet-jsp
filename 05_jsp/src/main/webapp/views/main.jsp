<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!-- include를 이용해서 공용 페이지 불러오기 -->
	<%-- <%@ include file="파일 경로" %> --%>
	<%@ include file="/views/common/header.jsp" %>
	<section>
		<h2>본문 내용</h2>
		<p><%=headerData %></p>
	</section>
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>