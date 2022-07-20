package us.puter.park.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.puter.park.domain.entity.ShortenUrl;

public interface ShortenUrlRepository extends JpaRepository<ShortenUrl, Long>{

	/**
	 * 실제 URL로 짧은 URI 정보 추출
	 *
	 * @param originalUrl
	 * @return
	 */
	ShortenUrl findShortenUrlByOriginalUrl(String originalUrl);

	/**
	 * 짧은 URI로 실제 URL 정보 추출
	 *
	 * @param shortenUri
	 * @return
	 */
	ShortenUrl findShortenUrlByShortenUri(String shortenUri);

}
