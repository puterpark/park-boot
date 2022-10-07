package us.puter.park.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "shortenurl_info")
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

	@Column(name = "accessIp")
	private String accessIp;

	@Column(name = "regDate")
	private Long regDate;

	@Builder
	public ShortenUrlInfo(ShortenUrl shortenUrl, String accessIp, Long regDate) {
		this.shortenUrl = shortenUrl;
		this.accessIp = accessIp;
		this.regDate = regDate;
	}

}
