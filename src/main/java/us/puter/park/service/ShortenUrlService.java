package us.puter.park.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.puter.park.domain.entity.ShortenUrl;
import us.puter.park.repository.ShortenUrlRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ShortenUrlService {

	private final ShortenUrlRepository shortenUrlRepository;

	/**
	 * 실제 URL로 짧은 URI 정보 추출
	 *
	 * @param originalUrl
	 * @return
	 */
	public ShortenUrl getShortenUriByOriginalUrl(String originalUrl) {

		return shortenUrlRepository.findShortenUrlByOriginalUrl(originalUrl);
	}

	/**
	 * 짧은 URI로 실제 URL 정보 추출
	 *
	 * @param shortenUri
	 * @return
	 */
	public ShortenUrl getOriginalUrlByShortenUri(String shortenUri) {

		return shortenUrlRepository.findShortenUrlByShortenUri(shortenUri);
	}

	/**
	 * 데이터 삽입
	 *
	 * @param urlInfo
	 * @return
	 */
	public ShortenUrl doInsertShortenUrl(ShortenUrl urlInfo) {

		return shortenUrlRepository.save(urlInfo);
	}

}
