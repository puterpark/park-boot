package us.puter.park.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.puter.park.domain.entity.Chat;
import us.puter.park.repository.ChatRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {

	private final ChatRepository chatRepository;

	/**
	 * chat 데이터 삽입
	 *
	 * @param chat
	 * @return
	 */
	public Chat doInsertChat(Chat chat) {

		return chatRepository.save(chat);
	}

}
