package us.puter.park.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.puter.park.domain.entity.ShortenUrlInfo;

public interface ShortenUrlInfoRepository extends JpaRepository<ShortenUrlInfo, Long> {

	/**
	 * regDate 이후의 접근 기록 갯수 조회
	 * @param regDate
	 * @return
	 */
	Long countByRegDateGreaterThanEqual(Long regDate);

}
