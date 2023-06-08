<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
	<header>
		<h1>UI테스트</h1>
		<div class="login-container">
			<form id="loginFrm" action="" onsubmit="return fn_validation();">
				<table>
					<tr>
						<td><input type="text" name="userId" id="userId"
							placeholder="아이디" value=""></td>
					</tr>
					<tr>
						<td><input type="password" name="password" id="password"
							placeholder="패스워드"></td>
						<td><input type="submit" value="로그인"></td>
					</tr>
					<tr>
						<td><label for="saveId"><input type="checkbox"
								name="saveId" id="saveId">아이디 저장</label>
								<input type="button" value="회원가입" onclick="location.replace('<%=request.getContextPath()%>/memberenroll.do')">
						</td>

					</tr>
				</table>
			</form>
		</div>
		<nav>
			<ul class="main-nav">
				<li class="home"><a href="">Home</a></li>
				<li id="notice"><a href="">공지사항</a></li>
				<li id="board"><a href="">게시판</a></li>
			</ul>
		</nav>
	</header>

	<section id="content">
		<h2 align="center" style="margin-top: 200px">UI테스트</h2>
	</section>
</body>
</html>