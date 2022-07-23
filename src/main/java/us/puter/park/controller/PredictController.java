package us.puter.park.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.puter.park.service.PredictService;
import us.puter.park.util.JsonUtil;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PredictController {

	private final PredictService predictService;

	/**
	 * 입력한 문장에 대한 감성(긍정/부정)분석기 (with. lucaseo[https://github.com/lucaseo])
	 *
	 * @param text
	 * @return
	 */
	@PostMapping(value = "/tools/predict")
	public @ResponseBody Map<String, Object> predict(String text) {

		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put(JsonUtil.RESULT, JsonUtil.FAILURE);

		predictService.getMoodByText(jsonMap, text);

		jsonMap.put(JsonUtil.RESULT, JsonUtil.SUCCESS);
		return jsonMap;
	}

}
