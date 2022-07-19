package us.puter.park.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

	@Value("${system.title}")
	private String title;

	@GetMapping(value = "/error")
	public String handlerError(Model model, HttpServletRequest req) {

		Object status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			int statusCode = Integer.valueOf(status.toString());

			model.addAttribute("title", title);

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				return "error/404";
			} else {
				return "error/500";
			}
		}

		return "error/500";
	}
}
