package us.puter.park.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.puter.park.domain.entity.ShortenUrl;
import us.puter.park.domain.entity.ShortenUrlInfo;

public interface ShortenUrlInfoRepository extends JpaRepository<ShortenUrlInfo, Long> {

	/**
	 * shortenUrl, RegDate를 통한 데이터 확인
	 *
	 * @param shortenUrl
	 * @param RegDate
	 * @return
	 */
	ShortenUrlInfo findShortenUrlInfoByShortenUrlAndRegDate(ShortenUrl shortenUrl, Long RegDate);

}
