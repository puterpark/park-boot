package us.puter.park.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "shortenurl")
@Getter
@Setter
@RequiredArgsConstructor
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
	private Long regDate;

	@Builder
	public ShortenUrl(Long shortenUrlUid, String originalUrl, String shortenUri, Long regDate) {
		this.shortenUrlUid = shortenUrlUid;
		this.originalUrl = originalUrl;
		this.shortenUri = shortenUri;
		this.regDate = regDate;
	}

}
