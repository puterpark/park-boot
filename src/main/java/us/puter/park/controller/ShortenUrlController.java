package us.puter.park.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import us.puter.park.domain.entity.ShortenUrl;
import us.puter.park.domain.entity.ShortenUrlInfo;
import us.puter.park.service.ShortenUrlService;
import us.puter.park.util.JsonUtil;
import us.puter.park.util.URLConnectionUtil;
import us.puter.park.util.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ShortenUrlController {

	private final ShortenUrlService shortenUrlService;

	@Value("${system.shorten-uri.length}")
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
			log.info("No mapping found for Shorten Url with shortenUri[" + shortenUri + "]");
			res.sendRedirect("/error/404");
			return;
		}

		try {
			String ip = Utility.getRemoteIP(req);
			String ipInfo = URLConnectionUtil.connect("http://ip-api.com/json/" + ip);

			ShortenUrlInfo shortenUrlInfo = ShortenUrlInfo.builder()
					.shortenUrl(shortenUrl)
					.accessIp(ip)
					.ipInfo(ipInfo)
					.regDate(Utility.getTimeMillis())
					.build();
			// 해당 shortenUrl의 일자별 접근 기록 저장
			shortenUrlService.doInsertShortenUrlInfo(shortenUrlInfo);
		} catch (Exception e) {
			log.error("occured error while insert shortenUrl info.", e);
		}

		res.sendRedirect(shortenUrl.getOriginalUrl());
	}

	/**
	 * ShortenURL DB 입력
	 *
	 * @param req
	 * @param res
	 * @param originalUrl
	 * @return
	 */
	@PostMapping(value = "/tools/shorten-url")
	public @ResponseBody Map<String, Object> shortenUrlInsert(HttpServletRequest req, HttpServletResponse res, @RequestParam String originalUrl) {

		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put(JsonUtil.RESULT, JsonUtil.FAILURE);

		String shortenUri = "";

		// 입력한 originalUrl의 중복 확인
		ShortenUrl urlInfo = shortenUrlService.getShortenUriByOriginalUrl(originalUrl);

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
