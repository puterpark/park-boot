package us.puter.park.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.puter.park.domain.entity.ShortenUrl;
import us.puter.park.domain.entity.ShortenUrlInfo;
import us.puter.park.service.ShortenUrlService;
import us.puter.park.util.JsonUtil;
import us.puter.park.util.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ShortenUrlController {

	private static final Logger logger = LoggerFactory.getLogger(ShortenUrlController.class);

	private final ShortenUrlService shortenUrlService;

	@Value("${system.shorten-uri-length}")
	private int shortenUriLength;

	/**
	 * shortenUri를 통하여 originalUrl로 redirect
	 *
	 * @param model
	 * @param req
	 * @param res
	 * @param shortenUri
	 * @throws IOException
	 */
	@GetMapping(value = "/{shortenUri}")
	public void index(Model model, HttpServletRequest req, HttpServletResponse res, @PathVariable String shortenUri) throws IOException {

		ShortenUrl shortenUrl = shortenUrlService.getOriginalUrlByShortenUri(shortenUri);

		// shortenUri와 매핑되는 originalUrl이 없을 경우 404 페이지로 이동
		if (shortenUrl == null) {
			logger.info("No mapping found for Shorten Url with shortenUri[" + shortenUri + "]");
			res.sendRedirect("/error/404");
			return;
		}

		String today = Utility.getTimeYYYYMMDD(System.currentTimeMillis());
		ShortenUrlInfo shortenUrlInfo = ShortenUrlInfo.builder()
											.shortenUrlUid(shortenUrl.getShortenUrlUid())
											.redirectCount(1L)
											.regDate(Long.parseLong(today))
											.build();
		// 해당 shortenUrl의 일자별 redirect 수 저장
		shortenUrlService.doInsertShortenUrlInfo(shortenUrlInfo);

		res.sendRedirect(shortenUrl.getOriginalUrl());
	}

	/**
	 * ShortenURL DB 입력
	 *
	 * @param req
	 * @param res
	 * @param urlInfo
	 * @return
	 */
	@PostMapping(value = "/tools/shorten-url")
	public @ResponseBody Map<String, Object> shortenUrlInsert(HttpServletRequest req, HttpServletResponse res, ShortenUrl urlInfo) {

		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put(JsonUtil.RESULT, JsonUtil.FAILURE);

		String shortenUri = "";
		String originalUrl = urlInfo.getOriginalUrl();

		// 입력한 originalUrl의 중복 확인
		urlInfo = shortenUrlService.getShortenUriByOriginalUrl(originalUrl);

		if (urlInfo != null) {
			// 중복일 경우 기존에 생성된 shortenUri 사용
			shortenUri = urlInfo.getShortenUri();
		} else {
			/*
			 * 중복이 아닐 경우
			 * 중복되지 않도록 랜덤한 shortenURI를 생성하고 DB 입력
			 */
			do {
				shortenUri = Utility.randomUri(shortenUriLength);
			} while (shortenUrlService.getOriginalUrlByShortenUri(shortenUri) != null);

			urlInfo = ShortenUrl.builder()
						.originalUrl(originalUrl)
						.shortenUri(shortenUri)
						.regDate(Utility.getTimeMillis())
						.build();

			shortenUrlService.doInsertShortenUrl(urlInfo);
		}

		jsonMap.put("shortenUri", shortenUri);
		jsonMap.put(JsonUtil.RESULT, JsonUtil.SUCCESS);
		return jsonMap;
	}

}
