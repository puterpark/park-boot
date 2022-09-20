package us.puter.park.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "chat")
@Getter
@Setter
@NoArgsConstructor
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
	public Chat(String nickname, String msg, Long regDate) {
		this.nickname = nickname;
		this.msg = msg;
		this.regDate = regDate;
	}

}
