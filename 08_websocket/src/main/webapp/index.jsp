<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>websocket test</title>
<script src="http://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<body>
	<div id="container">
		<input type="text" id="sender" placeholder="보내는 사람">
		<button id="chattingBtn">채팅 접속</button>
		<input type="text" id="receiver" placeholder="받는 사람">
		<br>
		<input type="text" id="sendMsg">
		&nbsp;<button id="sendBtn">전송</button>
	</div>
	<div id="msgcontainer"></div>
	<script type="text/javascript">
		//웹소캣 서버에 접속
		//ws:// Http프로토콜을 이용할 때 사용하는 주소
		//wss:// Https 프로토콜을 이용할 때 사용하는 주소
		let socket;
		$("#chattingBtn").click(e=>{
			
			socket=new WebSocket("ws://localhost:9090/<%=request.getContextPath()%>/chatting");
			
			//접속이 완료되면 실행될 함수를 등록
			socket.onopen=e=>{
				/* console.log(e);
				socket.send("안녕"); */
				//session에 내 정보를 저장
				sendMsg(new Message("접속",$("#sender").val(),"","",""));
			}
			
			//서버에서 전송된 메세지를 처리하는 함수 등록
			socket.onmessage=e=>{
				//sendText() 혹은 sendObject() 메소드를 사용했을 때 실행되는 메소드
				//서버에서 보낸 데이터를 처리하는 역할
				//console.log(e);
				//서버에서 보낸 데이터는 매개변수 객체의 data 속성에 저장되어있다.
				/* const msg=e.data.split(",");
				const sup=$("<sup>").text(msg[0]);
				const span=$("<span>").text(msg[1]).css("fontSize","20px"); */
				const sendData=JSON.parse(e.data);
				console.log(sendData);
				switch(sendData.type){
				case "채팅" : printData(sendData); break;
				case "알람" : alamPrint(sendData); break;
				}
				/* const textContainer=$("<div>").append(sup).append(span);
				$("#msgcontainer").append(textContainer); */
			}
		});
		const alamPrint=(sendData)=>{
			const h3=$("<h3>").text(sendData.data).css("color","olive");
			const container=$("<div>").css({"display":"flex","justifyContent":"center"});
			container.append(h3);
			$("#msgcontainer").append(container);
		}
		const printData=(sendData)=>{
			const sup=$("<sup>").text(sendData.sender).css("marginRight","2%");
			const span=$("<span>").text(sendData.data).css("fontWeight","bolder");
			const container=$("<div>").css("width","100%").append(sup).append(span);
			if(sendData.sender==$("#sender").val()){
				container.css({
					"display":"flex",
					"justifyContent":"end"
				});
			}else{
				container.css({
					"display":"flex",
					"justifyContent":"start"
				});
			}
			$("#msgcontainer").append(container);
		}
		$("#sendBtn").click(e=>{
			const data=$("#sendMsg").val();
			const sender=$("#sender").val();
			const receiver=$("#receiver").val();
			const sendData=new Message("채팅",sender,receiver,data,"");
			//sendMsg(sender+","+msg);
			sendMsg(sendData);
		});
		
		function sendMsg(data){
			//if(msg.length>0){
				socket.send(JSON.stringify(data));
			//}else throw new Error("메세지가 비어있습니다.");
		}
		
		//type : 채팅, 사용 현황, 알림
		//sender : 보낸 사람
		//receiver : 받는 사람
		//msg||data : 전송될 데이터
		//room : 
		
		class Message{
			constructor(type,sender,receiver,data,room){
				this.type=type;
				this.sender=sender;
				this.receiver=receiver;
				this.data=data;
				this.room=room;
			}
		}
	</script>
</body>
</html>