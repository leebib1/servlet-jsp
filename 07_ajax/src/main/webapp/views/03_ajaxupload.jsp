<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX 파일 업로드</title>
<script src="http://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<body>
	<h2>ajax를 이용해서 파일 업로드</h2>
	<input type="file" id="upFile" multiple>
	<button id="uploadBtn">업로드파일</button>
	<script type="text/javascript">
		$("#uploadBtn").click(e=>{
			//javascript에서 제공하는 FormData클래스를 이용한다.
			//key:value 형식으로 데이터를 넣을 수 있다.
			const form=new FormData();
			//append를 이용해서 서버에 전송할 데이터를 넣을 수 있다.
			const fileInput=$("#upFile");
			console.log(fileInput);
			$.each(fileInput[0].files,(i,f)=>{
				form.append("upfile"+i,f);
			});
			form.append("name","유병승");
			$.ajax({
				url:"<%=request.getContextPath()%>/fileUpload",
				data:form,
				type:"post",
				processData:false, //멀티파트폼으로 보내기 위해서 설정
				contentType:false,
				success:data=>{
					alert("업로드가 완료되었습니다.");
				},error:(r,m)=>{
					alert("업로드 실패했습니다."+m);
				},complete:()=>{
					$("#upFile").val(''); //완료되고나면 input 태그를 비워줌
				}
			});
		});
	</script>
	
	<h2>업로드 이미지 미리보기</h2>
	<img alt="" src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Default_pfp.svg/1200px-Default_pfp.svg.png"
	width="100" height="100" id="profile">
	<input type="file" style="display:none" id="profileInput" accept="image/*">
	
	<input type="file" id="upfiles" multiple accept="image/*">
	<div id="uploadpreview"></div>
	
	<script type="text/javascript">
		$("#profile").click(e=>{
			$("#profileInput").click();
		});
		
		//첨부파일이 업로드 돼서 값이 바뀌면 발생하는 이벤트
		$("#profileInput").change(e=>{
			const reader=new FileReader();
			reader.onload=e=>{ //파일이 업로드 되면 실행되는 이벤트 객체
				//e.target.result 속성에 변경된 파일이 나온다.
				$("#profile").attr("src",e.target.result);
			}
			//파일을 읽고나면 URL로 바꿔주는 것. 해당 값을 result에 대입
			reader.readAsDataURL(e.target.files[0]);
		});
		
		//이미지 여러개 출력
		$("#upfiles").change(e=>{
			//console.log(e.target.files);
			const files=e.target.files;
			//console.log(files.length);
			$.each(files,(i,f)=>{
				const reader=new FileReader();
				reader.onload=e=>{
					const img=$("<img>").attr({
						src:e.target.result,
						"width":"100",
						"height":"100"
					});
					$("#uploadpreview").append(img);
				}
				reader.readAsDataURL(f);
			});
		});
	</script>
	<style>
		#profile{
			border-radius:100px;
			border:3px solid black;
		}
	</style>
</body>
</html>