package us.puter.park.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import us.puter.park.service.MenuService;
import us.puter.park.util.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

	private final MenuService menuService;

	@Value("${system.title}")
	private String title;

	@GetMapping(value = "/")
	public String index(Model model, HttpServletRequest req, HttpServletResponse res) throws IOException {

		model.addAttribute("title", title);
		model.addAttribute("mode", "home");
		model.addAttribute("ip", Utility.getRemoteIP(req));

		// 메뉴 설정
		menuService.setModelFromMenu(model, null);

		return "pages/index/index";
	}

}
