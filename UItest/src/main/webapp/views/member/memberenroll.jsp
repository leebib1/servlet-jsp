<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css">
<script src="<%=request.getContextPath()%>/js/jquery-3.7.0.min.js"></script>
</head>
<body>
	<header>
		<h1>UI테스트</h1>
		<div class="login-container">
			<form id="loginFrm" action="">
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
	<section id=enroll-container>
        <h2>회원 가입 정보 입력</h2>
        <form action="<%=request.getContextPath() %>/memberenrollend.do" method="post" >
        <script type="text/javascript">
        	function inputDataCheck(){
        		const id=$("#userId_").val();
        		const pwd=$("#password_").val();
        		const pwdck=$("#password_2").val();
        		if(id.length<4){
        			alert("아이디는 4글자 이상 작성하세요.");
        			id.focus();
        			return false;
        		}else if(pwd==pwdck){
        			alert("비밀번호가 일치하지 않습니다.");
        			pwd.focus();
        			return false;
        		}
        	}
        	/* 선언한 태그 위에 적었기 때문에 ready함수 사용 */
        	$(()=>{
	        	const result=<%=request.getAttribute("result")%>
	        	if(result==0){
	        		alert("회원가입에 실패했습니다.");
	        	}
	        	});
        	});
        	
        </script>
        <table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" placeholder="4글자이상" name="userId" id="userId">
				</td>
			</tr>
			<tr>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td>
					<input type="password" name="password" id="password" ><br>
				</td>
			</tr>
			
			<tr>
				<th>패스워드확인</th>
				<td>	
					<input type="password" id="password2"><br>
				</td>
			</tr>
			<tr>
				<th></th>
				<td></td>
			</tr>  
			<tr>
				<th>이름</th>
				<td>	
				<input type="text"  name="userName" id="userName" ><br>
				</td>
			</tr>
			<tr>
				<th>나이</th>
				<td>	
				<input type="number" name="age" id="age"><br>
				</td>
			</tr> 
			<tr>
				<th>이메일</th>
				<td>	
					<input type="email" placeholder="abc@xyz.com" name="email" id="email"><br>
				</td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td>	
					<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" required><br>
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>	
					<input type="text" placeholder="" name="address" id="address"><br>
				</td>
			</tr>
			<tr>
				<th>성별 </th>
				<td>
					<input type="radio" name="gender" id="gender0"  value="M">>
					<label for="gender0">남</label>
					<input type="radio" name="gender" id="gender1" value="F">>
					<label for="gender1">여</label>
				</td>
			</tr>
			<tr>
				<th>취미 </th>
				<td>
					<input type="checkbox" name="hobby" id="hobby0" value="운동"><label for="hobby0">운동</label>
					<input type="checkbox" name="hobby" id="hobby1" value="등산"><label for="hobby1">등산</label>
					<input type="checkbox" name="hobby" id="hobby2" value="독서"><label for="hobby2">독서</label><br />
					<input type="checkbox" name="hobby" id="hobby3" value="게임"><label for="hobby3">게임</label>
					<input type="checkbox" name="hobby" id="hobby4" value="여행"><label for="hobby4">여행</label><br />
				</td>
			</tr>
		</table>
		<input type="submit" value="가입" onclick="return inputDataCheck();">
		<input type="reset" value="취소">
        </form>
    </section>
</body>
</html>