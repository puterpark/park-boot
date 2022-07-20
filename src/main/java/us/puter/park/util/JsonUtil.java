package us.puter.park.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	protected static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

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
			logger.error(e.getMessage(), e);
			return null;
		}
	}

}
