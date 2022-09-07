package us.puter.park.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import us.puter.park.util.Utility;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ServerEndpoint("/ws/chat")
public class WebSocketController extends Socket {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

	private static final Map<String, Session> SESSION_MAP = new HashMap<>();

	@OnOpen
	public void open(Session session) {
		SESSION_MAP.put(session.getId(), session);
		logger.info("webSocket session added[" + session.getId() + "], totalCnt[" + SESSION_MAP.size() + "]");
	}

	@OnMessage
	public void getMsg(String msg) {
		for (String key : SESSION_MAP.keySet()) {
			try {
				SESSION_MAP.get(key).getBasicRemote().sendText("[" + Utility.getTimeHHMMSS(Utility.getTimeMillis()) + "] " + msg + "|#|" + SESSION_MAP.size());
			} catch (IOException e) {
				logger.error("webSocket error occurred.", e);
			}
		}
	}

	@OnClose
	public void close(Session session) {
		SESSION_MAP.remove(session.getId());
		logger.info("webSocket session removed[" + session.getId() + "], totalCnt[" + SESSION_MAP.size() + "]");
	}

}
