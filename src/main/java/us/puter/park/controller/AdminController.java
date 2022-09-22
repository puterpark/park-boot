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

		Long todayRedirectCount = shortenUrlService.getRedirectCountByRegDate(Utility.getTimeMillis());
		List<ShortenUrlDto> shortenUrlTop5List = shortenUrlService.getShortenUrlListTop5(-7);

		model.addAttribute("todayRedirectCount", todayRedirectCount);
		model.addAttribute("shortenUrlTop5List", shortenUrlTop5List);

		return "pages/admin/index";
	}
}
