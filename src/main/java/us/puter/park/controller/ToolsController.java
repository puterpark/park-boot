package us.puter.park.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import us.puter.park.service.MenuService;
import us.puter.park.util.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ToolsController {

	private final MenuService menuService;

	@Value("${system.title}")
	private String title;

	@Value("${system.tools}")
	private String modeString;

	@Value("${system.qr-code-size}")
	private int qrCodeSize;

	@GetMapping(value = "/tools/{mode}")
	public String index(Model model, HttpServletRequest req, HttpServletResponse res, @PathVariable String mode) throws IOException {

		String[] modeArr = modeString.split(";");

		boolean flag = false;

		/*
		 * system.tools에 정의된 메뉴만 이동 가능
		 */
		for (String modeStr : modeArr) {
			if (mode.equals(modeStr)) {
				flag = true;
			}
		}

		if (!flag) {
			log.info("No mapping found for HTTP request with URI [{" + req.getRemoteHost() + "} " + req.getRequestURI() + "] in DispatcherServlet with name 'servlet'");
			return "redirect:/error/404";
		}

		model.addAttribute("title", title);
		model.addAttribute("ip", Utility.getRemoteIP(req));

		// 메뉴 설정
		menuService.setModelFromMenu(model, mode);

		// qrcode로 접속할 경우, qrCodeSize를 모델에 내린다.
		if ("qrcode".equals(mode)) {
			model.addAttribute("qrCodeSize", qrCodeSize);
		}

		return "pages/tools/" + mode;
	}
}
