package us.puter.park.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

	/**
	 * regDate를 통한 redirectCount의 합계 조회
	 * @param regDate
	 * @return
	 */
	@Query(value = "select sum(sui.redirectCount) from ShortenUrlInfo sui where sui.regDate = :regDate")
	Long selectRedirectCountByRegDate(Long regDate);

}
