package us.puter.park.service;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.puter.park.domain.entity.ShortenUrl;
import us.puter.park.domain.entity.ShortenUrlInfo;
import us.puter.park.repository.ShortenUrlInfoRepository;
import us.puter.park.repository.ShortenUrlRepository;
import us.puter.park.service.dto.ShortenUrlDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ShortenUrlService {

	private final ShortenUrlRepository shortenUrlRepository;

	private final ShortenUrlInfoRepository shortenUrlInfoRepository;

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
	 * shortenUrl 데이터 삽입
	 *
	 * @param shortenUrl
	 * @return
	 */
	public ShortenUrl doInsertShortenUrl(ShortenUrl shortenUrl) {

		return shortenUrlRepository.save(shortenUrl);
	}

	/**
	 * shortenUrl의 접근 기록 저장
	 *
	 * @param shortenUrlInfo
	 * @return
	 */
	public void doInsertShortenUrlInfo(ShortenUrlInfo shortenUrlInfo) {

		shortenUrlInfoRepository.save(shortenUrlInfo);
	}

	/**
	 * 입력한 시간 이후의 접근 기록 횟수 추출
	 * @param date
	 * @return
	 */
	public Long getRedirectCountByRegDate(Long date) {

		Long count = shortenUrlInfoRepository.countByRegDateGreaterThanEqual(LocalDate.now().atStartOfDay());
		return count == null ? 0L : count;
	}

	/**
	 * 현재날짜에서 day만큼 뺀 날짜부터 접근 기록 수가 가장 많은 상위 5개 shortenUrl 조회
	 * @param day
	 * @return
	 */
	public List<ShortenUrlDto> getShortenUrlListTop5(int day) {

		LocalDateTime ldt = LocalDate.now().atStartOfDay();
		ldt = ldt.plusDays(day);
		return shortenUrlRepository.findShortenUrlListTop5(ldt);
	}

	/**
	 * 도표 사용을 위한 List&lt;ShortenUrlDto&gt; → JsonString 변환
	 * @param list
	 * @return
	 */
	public String toJsonStringForChart(List<ShortenUrlDto> list) {

		JSONArray jsonArr = new JSONArray();

		for (ShortenUrlDto dto : list) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("x", dto.getShortenUri());
			jsonObj.put("y", dto.getRedirectCount());
			jsonArr.add(jsonObj);
		}

		return jsonArr.toJSONString();
	}

	/**
	 * 입력한 시간 이후의 가장 접근을 많이한 IP 추출
	 * @param date
	 * @return
	 */
	public String getMostAccessIp(Long date) {

		String mostAccessIp = "x.x.x.x";

		ShortenUrlDto shortenUrlDto = shortenUrlRepository.findMostAccessIp(LocalDate.now().atStartOfDay());

		if (shortenUrlDto != null) {
			mostAccessIp = shortenUrlDto.getAccessIp();
		}

		return mostAccessIp;
	}

}
