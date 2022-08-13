package us.puter.park.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(ProfileInterceptor.class);

	private String activeProfile = System.getProperty("active.profile");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		activeProfile = StringUtils.isBlank(activeProfile) ? "live" : activeProfile;
		request.setAttribute("activeProfile", activeProfile);

		return true;
	}

}
