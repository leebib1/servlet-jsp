<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- header.jsp에 import해놨기 때문에 에러가 발생하지 않음 -->
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
</head>
<body>
<%
	Member infoMember=(Member)request.getAttribute("infoMember");
	String[] checkData=new String[5];
	if(infoMember.getHobby()!=null){
		for(String h:infoMember.getHobby()){
			switch(h){
			case "운동" : checkData[0]="checked"; break;
			case "등산" : checkData[1]="checked"; break;
			case "독서" : checkData[2]="checked"; break;
			case "게임" : checkData[3]="checked"; break;
			case "여행" : checkData[4]="checked"; break;
			}
		}
	}
%>
	<%@ include file="/views/common/header.jsp" %>
	<section id=enroll-container>
		<h2>회원 정보 수정</h2>
		<form id="memberFrm" method="post" >
			<table>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="userId" id="userId_" value="<%=infoMember.getUserId() %>" readonly>
					</td>
				</tr>
				<!-- <tr>
					<th>패스워드</th>
					<td>
						<input type="password" name="password" id="password_">
					</td>
				</tr>
				<tr>
					<th>패스워드확인</th>
					<td>	
						<input type="password" id="password_2"><br>
					</td>
				</tr>   -->
				<tr>
					<th>이름</th>
					<td>	
					<input type="text"  name="userName" id="userName" value="<%=infoMember.getUserName() %>" required><br>
					</td>
				</tr>
				<tr>
					<th>나이</th>
					<td>	
					<input type="number" name="age" id="age" value="<%=infoMember.getAge() %>"><br>
					</td>
				</tr> 
				<tr>
					<th>이메일</th>
					<td>	
						<input type="email" placeholder="abc@xyz.com" name="email" id="email" value="<%=infoMember.getEmail() %>"><br>
					</td>
				</tr>
				<tr>
					<th>휴대폰</th>
					<td>	
						<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11"
						value="<%=infoMember.getPhone() %>"><br>
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>	
						<input type="text" placeholder="" name="address" id="address" value="<%=infoMember.getAddress() %>"><br>
					</td>
				</tr>
				<tr>
					<th>성별 </th>
					<td>
						DB정보에 따라 분기처리할것
							<input type="radio" name="gender" id="gender0" value="M"
							<%= infoMember.getGender()=='M'?"checked":"" %>>
							<label for="gender0">남</label>
							<input type="radio" name="gender" id="gender1" value="F"
							<%= infoMember.getGender()=='F'?"checked":"" %>>
							<label for="gender1">여</label>
					</td>
				</tr>
				<tr>
					<th>취미 </th>
					<td>
						<input type="checkbox" name="hobby" id="hobby0" value="운동" <%=checkData[0] %>><label for="hobby0">운동</label>
						<input type="checkbox" name="hobby" id="hobby1" value="등산" <%=checkData[1] %>><label for="hobby1">등산</label>
						<input type="checkbox" name="hobby" id="hobby2" value="독서" <%=checkData[2] %>><label for="hobby2">독서</label><br />
						<input type="checkbox" name="hobby" id="hobby3" value="게임" <%=checkData[3] %>><label for="hobby3">게임</label>
						<input type="checkbox" name="hobby" id="hobby4" value="여행" <%=checkData[4] %>><label for="hobby4">여행</label><br />
					</td>
				</tr>
			</table>
			<input type="button" value="비밀번호 변경" onclick="fn_updatePassword();"/>
			<input type="button" value="정보수정" onclick="fn_updateMember();"/>
			<input type="button" value="탈퇴"/>
		</form>
		<script type="text/javascript">
			const fn_updateMember=()=>{
				//form 전송
				$("#memberFrm").attr("action","<%=request.getContextPath()%>/member/updateEndMember.do").submit();
			}
			const fn_updatePassword=()=>{
				open("<%=request.getContextPath()%>/member/updatePassword.do?userId=<%=infoMember.getUserId() %>"
						,"_blank","width=400 height=210 left=500 top=200");
				
			}
		</script>
	</section>
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>