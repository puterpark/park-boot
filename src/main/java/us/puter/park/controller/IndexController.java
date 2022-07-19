package us.puter.park.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import us.puter.park.service.MenuService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	private final MenuService menuService;

	@GetMapping(value = "/")
	public String index(Model model, HttpServletRequest req, HttpServletResponse res) throws IOException {

		String title = "PUTER.US";
		model.addAttribute("title", title);
		model.addAttribute("mode", "home");

		// 메뉴 설정
		menuService.setModelFromMenu(model, null);

		return "pages/index/index";
	}
}
