package us.puter.park.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import us.puter.park.service.MenuService;
import us.puter.park.service.ShortenUrlService;
import us.puter.park.service.dto.ShortenUrlDto;
import us.puter.park.util.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AdminController {

	private final MenuService menuService;

	private final ShortenUrlService shortenUrlService;

	@Value("${system.title}")
	private String title;

	@GetMapping(value = "/admin")
	public String admin(Model model, HttpServletRequest req, HttpServletResponse res) {

		model.addAttribute("title", title);
		model.addAttribute("mode", "home");

		// 메뉴 설정
		menuService.setModelFromMenu(model, null);

		Long currentTime = Utility.getTimeMillis();

		// 현재 리다이렉트 수 총합
		Long todayRedirectCount = shortenUrlService.getRedirectCountByRegDate(currentTime);
		model.addAttribute("todayRedirectCount", todayRedirectCount);

		// 현재 가장 많이 접근한 IP
		String todayMostAccessIp = shortenUrlService.getMostAccessIp(currentTime);
		model.addAttribute("todayMostAccessIp", todayMostAccessIp);

		// 7일 전부터 오늘까지 리다이렉트 수가 가장 많은 상위 5개
		List<ShortenUrlDto> shortenUrlList = shortenUrlService.getShortenUrlListTop5(-7);
		model.addAttribute("top5day7", shortenUrlService.toJsonStringForChart(shortenUrlList));

		// 30일 전부터 오늘까지 리다이렉트 수가 가장 많은 상위 5개
		shortenUrlList = shortenUrlService.getShortenUrlListTop5(-30);
		model.addAttribute("top5day30", shortenUrlService.toJsonStringForChart(shortenUrlList));

		return "pages/admin/index";
	}
}
