package us.puter.park.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import us.puter.park.util.Utility;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

@Controller
@ServerEndpoint("/ws/chat/{nickname}")
public class WebSocketController extends Socket {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

	private static final Map<String, Session> SESSION_MAP = new HashMap<>();

	@OnOpen
	public void open(Session session, @PathParam("nickname") String nickname) throws Exception {
		String sessionId = session.getId();
		SESSION_MAP.put(sessionId, session);
		logger.info("webSocket session added. id[" + sessionId + "], nickname[" + nickname + "], totalCnt[" + SESSION_MAP.size() + "]");
	}

	@OnMessage
	public void getMsg(String msg) {
		broadCast(msg);
	}

	@OnClose
	public void close(Session session, @PathParam("nickname") String nickname) {
		String sessionId = session.getId();
		SESSION_MAP.remove(sessionId);
		broadCast("(퇴장) " + nickname);
		logger.info("webSocket session removed. id[" + sessionId + "], nickname[" + nickname + "], totalCnt[" + SESSION_MAP.size() + "]");
	}

	private void broadCast(String msg) {
		for (String key : SESSION_MAP.keySet()) {
			try {
				SESSION_MAP.get(key).getBasicRemote().sendText("[" + Utility.getTimeHHMMSS(Utility.getTimeMillis()) + "] " + msg + "|#|" + SESSION_MAP.size());
			} catch (IOException e) {
				logger.error("webSocket error occurred.", e);
			}
		}
	}

}
