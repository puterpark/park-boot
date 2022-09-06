package us.puter.park.controller;

import org.springframework.stereotype.Controller;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Controller
@ServerEndpoint("/test/websocket")
public class WebSocketController extends Socket {

	private static final List<Session> SESSION_LIST = new ArrayList<>();

	@OnOpen
	public void open(Session newUser) {
		SESSION_LIST.add(newUser);
	}

	@OnMessage
	public void getMsg(Session receiveSession, String msg) {
		for (int i = 0; i < SESSION_LIST.size(); i ++) {
			if (!receiveSession.getId().equals(SESSION_LIST.get(i).getId())) {
				try {
					SESSION_LIST.get(i).getBasicRemote().sendText("상대 : " + msg);
				} catch (IOException e) {
				}
			} else {
				try {
					SESSION_LIST.get(i).getBasicRemote().sendText("나 : " + msg);
				} catch (IOException e) {
				}
			}
		}
	}
}
