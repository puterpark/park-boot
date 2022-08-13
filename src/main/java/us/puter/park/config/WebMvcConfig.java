package us.puter.park.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import us.puter.park.interceptor.ProfileInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ProfileInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/resources/**", "/error");
	}

}
