package us.puter.park.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import us.puter.park.domain.entity.ShortenUrlInfo;

public interface ShortenUrlInfoRepository extends JpaRepository<ShortenUrlInfo, Long> {

	/**
	 * ShortenUrlUid, RegDate를 통한 데이터 확인
	 *
	 * @param ShortenUrlUid
	 * @param RegDate
	 * @return
	 */
	ShortenUrlInfo findShortenUrlInfosByShortenUrlUidAndRegDate(Long ShortenUrlUid, Long RegDate);

}
