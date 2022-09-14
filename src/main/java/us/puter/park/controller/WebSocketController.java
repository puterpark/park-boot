package us.puter.park.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import us.puter.park.config.ServerEndpointConfig;
import us.puter.park.domain.entity.Chat;
import us.puter.park.service.ChatService;
import us.puter.park.util.Utility;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@ServerEndpoint(value = "/ws/chat/{nickname}", configurator = ServerEndpointConfig.class)
public class WebSocketController extends Socket {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

	private static final Map<String, Session> SESSION_MAP = new HashMap<>();

	private static final Map<String, String> NICKNAME_MAP = new HashMap<>();

	private final ChatService chatService;

	@OnOpen
	public void open(Session session, @PathParam("nickname") String nickname) throws Exception {

		if (NICKNAME_MAP.get(nickname) != null) {
			// 중복되는 닉네임일 경우 세션을 닫아서 닉네임을 다시 받게 함.
			session.close();
			return;
		}

		String sessionId = session.getId();
		SESSION_MAP.put(sessionId, session);
		NICKNAME_MAP.put(nickname, sessionId);
		broadCast("<span class='badge badge-success'>[" + nickname + "]님이 입장했습니다.</span>", "notice", 0);
		logger.info("webSocket session added. id[" + sessionId + "], nickname[" + nickname + "], totalCnt[" + SESSION_MAP.size() + "]");
	}

	@OnMessage
	public void getMsg(String msg, @PathParam("nickname") String nickname) {
		broadCast(msg, nickname, 1);
	}

	@OnClose
	public void close(Session session, @PathParam("nickname") String nickname) {

		String sessionId = session.getId();

		if (NICKNAME_MAP.get(nickname) != null && SESSION_MAP.get(sessionId) == null) {
			// 닉네임이 중복되고 세션ID가 등록되어 있지 않으면 세션을 종료한다.
			return;
		}

		SESSION_MAP.remove(sessionId);
		NICKNAME_MAP.remove(nickname);
		broadCast("<span class='badge badge-danger'>[" + nickname + "]님이 퇴장했습니다.</span>", "notice", 0);
		logger.info("webSocket session removed. id[" + sessionId + "], nickname[" + nickname + "], totalCnt[" + SESSION_MAP.size() + "]");
	}

	/**
	 * 채팅 메시지 전체 전달
	 * @param msg
	 * @param nickname
	 * @param mode 0:공지용, 1:그외
	 */
	private void broadCast(String msg, String nickname, int mode) {
		long currentTime = Utility.getTimeMillis();

		for (String key : SESSION_MAP.keySet()) {
			try {
				SESSION_MAP.get(key).getBasicRemote().sendText("<span class='badge badge-dark'>" + Utility.getTimeHHMMSS(currentTime) + "</span> " + msg + "|#|" + SESSION_MAP.size());
			} catch (IOException e) {
				logger.error("webSocket error occurred.", e);
			}
		}

		if (mode == 0) {
			msg = Utility.removeHtmlTag(msg);
		} else if (mode == 1) {
			msg = msg.split("\\] : ")[1];
		}

		try {
			Chat chat = Chat.builder()
					.nickname(nickname)
					.msg(msg)
					.regDate(currentTime)
					.build();
			chatService.doInsertChat(chat);
		} catch (Exception e) {
			logger.error("occurred error during insert db. [" + nickname +  "/" + msg + "]");
		}
	}

}
