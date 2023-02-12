package us.puter.park.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShortenUrlDto {

	private Long shortenUrlUid;
	private String shortenUri;
	private Long redirectCount;
	private String accessIp;

}
