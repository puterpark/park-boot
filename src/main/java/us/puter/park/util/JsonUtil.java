package us.puter.park.util;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
public class JsonUtil {

	public static final String RESULT = "result";

	public static final String FORMRESULT = "success";

	public static final boolean SUCCESS = true;

	public static final boolean FAILURE = false;

	private static ObjectMapper jsonMapper = new ObjectMapper();

	static {
		jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static String toJson(Object obj) {

		try {
			return jsonMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			return "{error:json-exception}";
		}
	}

	public static <T> T toBean(String json, Class<T> clazz) {
		try {
			return jsonMapper.readValue(json, clazz);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

}
