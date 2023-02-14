package us.puter.park.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shortenurl")
@Getter
@Setter
@NoArgsConstructor
public class ShortenUrl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shortenUrlUid")
	private Long shortenUrlUid;

	@Column(name = "originalUrl")
	private String originalUrl;

	@Column(name = "shortenUri")
	private String shortenUri;

	@Column(name = "regDate")
	private LocalDateTime regDate;

	@Builder
	public ShortenUrl(String originalUrl, String shortenUri, LocalDateTime regDate) {
		this.originalUrl = originalUrl;
		this.shortenUri = shortenUri;
		this.regDate = regDate;
	}

}
