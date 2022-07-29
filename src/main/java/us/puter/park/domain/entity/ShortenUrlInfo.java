package us.puter.park.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "shortenurl_info")
@Getter
@Setter
@RequiredArgsConstructor
public class ShortenUrlInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shortenUrlInfoUid")
	private Long shortenUrlInfoUid;

	@Column(name = "shortenUrlUid")
	private Long shortenUrlUid;

	@Column(name = "redirectCount")
	private Long redirectCount;

	@Column(name = "regDate")
	private Long regDate;

	@Builder
	public ShortenUrlInfo(Long shortenUrlInfoUid, Long shortenUrlUid, Long redirectCount, Long regDate) {
		this.shortenUrlInfoUid = shortenUrlInfoUid;
		this.shortenUrlUid = shortenUrlUid;
		this.redirectCount = redirectCount;
		this.regDate = regDate;
	}

}
