package us.puter.park.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "shortenurl_info")
@Getter
@Setter
@NoArgsConstructor
public class ShortenUrlInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shortenUrlInfoUid")
	private Long shortenUrlInfoUid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shortenUrlUid")
	private ShortenUrl shortenUrl;

	@Column(name = "redirectCount")
	private Long redirectCount;

	@Column(name = "regDate")
	private Long regDate;

	@Builder
	public ShortenUrlInfo(ShortenUrl shortenUrl, Long redirectCount, Long regDate) {
		this.shortenUrl = shortenUrl;
		this.redirectCount = redirectCount;
		this.regDate = regDate;
	}

}
