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
import us.puter.park.util.Utility;

import java.util.Calendar;
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
	 * shortenUrl의 일자별 redirect 수 저장
	 *
	 * @param shortenUrlInfo
	 * @return
	 */
	public void doInsertShortenUrlInfo(ShortenUrlInfo shortenUrlInfo) {

		ShortenUrl shortenUrl = shortenUrlInfo.getShortenUrl();
		Long regDate = shortenUrlInfo.getRegDate();

		// 값 존재 여부 확인
		ShortenUrlInfo orgShortenUrlInfo = shortenUrlInfoRepository.findShortenUrlInfoByShortenUrlAndRegDate(shortenUrl, regDate);

		if (orgShortenUrlInfo == null) {
			// 새로 insert
			shortenUrlInfoRepository.save(shortenUrlInfo);
		} else {
			// redirectCount의 값만 바꾼다.
			Long redirectCount = orgShortenUrlInfo.getRedirectCount();
			orgShortenUrlInfo.setRedirectCount(++redirectCount);
			shortenUrlInfoRepository.save(orgShortenUrlInfo);
		}
	}

	/**
	 * 입력한 시간에 따라 redirectCount 합계 추출
	 * @param date
	 * @return
	 */
	public Long getRedirectCountByRegDate(Long date) {
		String yyyyMMdd = Utility.getTimeYYYYMMDD(date);
		Long count = shortenUrlInfoRepository.selectRedirectCountByRegDate(Long.parseLong(yyyyMMdd));
		return count == null ? 0L : count;
	}

	/**
	 * 현재날짜에서 day만큼 뺀 날짜부터 리다이렉트 수가 가장 많은 상위 5개 shortenUrl 조회
	 * @param day
	 * @return
	 */
	public List<ShortenUrlDto> getShortenUrlListTop5(int day) {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, day);
		String yyyyMMdd = Utility.getTimeYYYYMMDD(cal.getTimeInMillis());
		return shortenUrlRepository.findShortenUrlListTop5(Long.parseLong(yyyyMMdd));
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

}
