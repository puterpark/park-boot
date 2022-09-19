package us.puter.park.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import us.puter.park.domain.entity.ShortenUrlInfo;

public interface ShortenUrlInfoRepository extends JpaRepository<ShortenUrlInfo, Long> {

	/**
	 * shortenUrlUid, regDate를 통한 데이터 확인
	 *
	 * @param shortenUrlUid
	 * @param regDate
	 * @return
	 */
	ShortenUrlInfo findShortenUrlInfosByShortenUrlUidAndRegDate(Long shortenUrlUid, Long regDate);

	/**
	 * regDate를 통한 redirectCount의 합계 조회
	 * @param regDate
	 * @return
	 */
	@Query(value = "select sum(sui.redirectCount) from ShortenUrlInfo sui where sui.regDate = :regDate")
	Long selectRedirectCountByRegDate(Long regDate);

}
