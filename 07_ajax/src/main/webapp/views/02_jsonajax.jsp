<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSON으로 데이터 가져오기</title>
<script src="http://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<body>
	<h2>json을 이용해서 데이터 통신하기</h2>
	<p>서버와 클라이언트가 데이터를 주고 받을 때 문자열을 js object 표현 방식으로 처리하는 것<br>
		[] : 배열, {} : 객체
	</p>
	<ul>
		java에서 생성한 객체(vo.Collection 프레임워크들)를 자동으로 변환해주는 라이브러리를 사용
		<li>jsonsimple.jar : JSONObject 클래스, JSONArray 클래스를 이용해서 처리한다.</li>
		<li>gson.jar : Gson 클래스 이용한다.</li>
		<li>jackson.jar : ObjectMapper 클래스를 이용한다. 스프링에서 기본적으로 사용한다.</li>
	</ul>
	
	<button id="jsonBtn">jsonSimple</button>
	<button id="gsonBtn">GsonSimple</button>
	<button id="jsonparse">JSon parse</button>
	<script type="text/javascript">
		$("#jsonparse").click(e=>{
			fetch("<%=request.getContextPath()%>/gsontest.do",
				{
					method:"post",
					body:JSON.stringify({"userId":"bsyoo",
						"password":"1234",
						"age":19,
						"userName":"유병승",
						"gender":"M",
						"email":"teacherdev09@gmail.com"})
				}).then(response=>response.json()).then(data=>{
						console.log(data);
					});
		});
		$("#jsonBtn").click(e=>{
			$.get("<%=request.getContextPath()%>/basicJson.do",
				function(data){
				/* console.log(data);
				console.log(data.userId);
				console.log(data["age"]+50);
				if(data.flag){
					alert("flag 실행");
				} */
				console.log(data);
			});
		});
		$("#gsonBtn").click(e=>{
			$.get("<%=request.getContextPath()%>/gsontest.do",
					data=>{
						console.log(data);	
					});
		});
	</script>
</body>
</html>