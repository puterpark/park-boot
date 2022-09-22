package us.puter.park.repository;

import us.puter.park.service.dto.ShortenUrlDto;

import java.util.List;

public interface ShortenUrlRepositoryCustom {

	/**
	 * date 이후의 리다이렉트 수가 가장 많은 상위 5개 shortenUrl 조회
	 * @param date
	 * @return
	 */
	List<ShortenUrlDto> findShortenUrlListTop5(Long date);

}
