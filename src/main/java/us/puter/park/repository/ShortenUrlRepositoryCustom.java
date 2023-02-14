package us.puter.park.repository;

import us.puter.park.service.dto.ShortenUrlDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ShortenUrlRepositoryCustom {

	/**
	 * date 이후의 리다이렉트 수가 가장 많은 상위 5개 shortenUrl 조회
	 * @param date
	 * @return
	 */
	List<ShortenUrlDto> findShortenUrlListTop5(LocalDateTime date);

	/**
	 * date 이후의 가장 많이 접근한 IP 조회
	 * @param date
	 * @return
	 */
	ShortenUrlDto findMostAccessIp(LocalDateTime date);

}
