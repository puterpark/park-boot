package us.puter.park.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import us.puter.park.service.MenuService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ToolsController {

	private static final Logger logger = LoggerFactory.getLogger(ToolsController.class);

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
			logger.info("No mapping found for HTTP request with URI [{" + req.getRemoteHost() + "} " + req.getRequestURI() + "] in DispatcherServlet with name 'servlet'");
			return "redircet:/error";
		}

		model.addAttribute("title", title);

		// 메뉴 설정
		menuService.setModelFromMenu(model, mode);

		// qrcode로 접속할 경우, qrCodeSize를 모델에 내린다.
		if ("qrcode".equals(mode)) {
			model.addAttribute("qrCodeSize", qrCodeSize);
		}

		return "pages/tools/" + mode;
	}
}
