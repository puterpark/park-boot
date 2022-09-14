package us.puter.park.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "chat")
@Getter
@Setter
@RequiredArgsConstructor
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chatUid")
	private Long chatUid;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "msg")
	private String msg;

	@Column(name = "regDate")
	private Long regDate;

	@Builder
	public Chat(Long chatUid, String nickname, String msg, Long regDate) {
		this.chatUid = chatUid;
		this.nickname = nickname;
		this.msg = msg;
		this.regDate = regDate;
	}

}
