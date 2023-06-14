package com.websocket.controller;

import java.io.IOException;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.websocket.vo.Message;


@ServerEndpoint(value="/chatting",
		decoders= {JsonDecoder.class}, //json 형식의 데이터를 디코딩
		encoders= {JsonEncoder.class})
public class CattingServer {
	//private List<Session> clients=new ArrayList();
	@OnOpen
	public void open(Session session, EndpointConfig config) {
		//클라이언트가 접속 요청하면 자동 실행되는 메소드
		System.out.println(session.getId());
		System.out.println("서버 접속!");
		
		//clients.add(session);
	}
	
	@OnMessage
	public void message(Session session, Message m) {
		//js에서 socket.send("message") 메소드를 실행했을 때 자동 실행되는 메소드
		//두번째 매개변수는 send() 함수의 인자값 : 클라이언트가 보낸 데이터
		System.out.println(m);
		//Message m=new Gson().fromJson(msg, Message.class);
		System.out.println(m);
		switch(m.getType()) {
			case "접속" : addClient(session,m); break;
			case "채팅" : sendMessage(session,m); break;
		}
		
		//접속한 사용자에게 받은 메시지를 전달
		//send 메소드를 실행한 session의 Session 객체가 매개변수로 들어온다.
		
//		//접속한 sesssion을 관리하는 메소드
//		Set<Session> clients=session.getOpenSessions(); //열린 세션을 전부 가져옴
//		//System.out.println(clients.size());
//		//session을 구분할 값을 저장
//		session.getUserProperties().put("msg", msg);
//		
//		try {
//			for(Session client:clients) {
//				client.getBasicRemote().sendText(msg); //접속한 사용자에게 전부 보냄
//			}
//			//session.getBasicRemote().sendText(msg);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	private void addClient(Session session, Message m) {
		//session을 구분할 수 있는 데이터 저장
		session.getUserProperties().put("msg", m);
		sendMessage(session,Message.builder().type("알람")
				.data(m.getSender()+"님이 접속하셨습니다.").build());
	}
	
	private void sendMessage(Session session, Message m) {
		//접속한 클라이언트들한테 메세지 전송해주는 메소드
		Set<Session> clients=session.getOpenSessions();
		try {
		if(m.getReceiver()==null||m.getReceiver().isBlank()) {
			//전체 접속자에게 전송
			for(Session client:clients) {
				//client.getBasicRemote().sendText(new Gson().toJson(m));
				try {
					client.getBasicRemote().sendObject(m);
				} catch (EncodeException e) {
					e.printStackTrace();
				}
			}
		}else {
			//해당 접속자에게만 전송
			for(Session client:clients) {
				Message c=(Message)client.getUserProperties().get("msg");
				if(c.getSender().equals(m.getReceiver())) {
						try {
							client.getBasicRemote().sendObject(m);
						} catch (EncodeException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
