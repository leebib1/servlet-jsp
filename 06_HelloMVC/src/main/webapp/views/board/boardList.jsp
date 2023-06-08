<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.web.board.dto.Board" %>
<%@ include file="/views/common/header.jsp"%>


<%
	List<Board> boardList=(List)request.getAttribute("boardList");
%>
<section id="board-container">
	<h2>게시판</h2>
	<table id="tbl-board">
		<%if(loginMember!=null){ %>
			<tr>
				<td colspan="6">
					<button onclick="location.href='<%=request.getContextPath()%>/board/boardwrite'">
					글 작성</button>
				</td>
			</tr>
		<%	}%>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>첨부파일</th>
			<th>조회수</th>
		</tr>
		<% if(boardList!=null){
			for(Board b:boardList){%>
			<tr>
				<td><%=b.getBoardNo() %></td>
				<td onclick="location.href='<%=request.getContextPath() %>/board/boardContent?boardNo=<%=b.getBoardNo() %>'">
					<%=b.getBoardTitle() %>
				</td>
				<td><%=b.getBoardWriter() %></td>
				<td><%=b.getBoardDate() %></td>
				<td>
					<% if(b.getBoardOriginalFileName()!=null){ %>
						<img src="<%=request.getContextPath() %>/images/file.png" width="20">
					<%} %>
				</td>
				<td><%=b.getBoardReadConut() %></td>
			</tr>
		<%	}
		}else{%>
			<tr><td colspan="6">출력될 데이터가 없습니다</td></tr>
		<%	}%>
		
	</table>
	<div id="pageBar">
		<%=request.getAttribute("pageBar") %>
	</div>
</section>


<style>
section#board-container {
	width: 600px;
	margin: 0 auto;
	text-align: center;
}

section#board-container h2 {
	margin: 10px 0;
}

table#tbl-board {
	width: 100%;
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
	clear: both;
}

table#tbl-board th, table#tbl-board td {
	border: 1px solid;
	padding: 5px 0;
	text-align: center;
}
/*글쓰기버튼*/
input#btn-add {
	float: right;
	margin: 0 0 15px;
}
/*페이지바*/
div#pageBar {
	margin-top: 10px;
	text-align: center;
	background-color: rgba(0, 188, 212, 0.3);
}

div#pageBar span.cPage {
	color: #0066ff;
}
</style>
<%@ include file="/views/common/footer.jsp"%>
</body>
</html>