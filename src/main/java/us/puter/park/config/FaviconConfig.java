package us.puter.park.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.*;

@Configuration
public class FaviconConfig {

	@Bean
	public SimpleUrlHandlerMapping faviconHandlerMapping() {
		SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();

		Map<String, ResourceHttpRequestHandler> map = new HashMap<>();
		ResourceHttpRequestHandler faviconRequestHandler = faviconRequestHandler();

		// ico
		map.put("/favicon.ico", faviconRequestHandler);
		map.put("/favicon-16x16.png", faviconRequestHandler);
		map.put("/favicon-32x32.png", faviconRequestHandler);

		// apple
		map.put("/apple-touch-icon.png", faviconRequestHandler);
		map.put("/apple-touch-icon-57x57.png", faviconRequestHandler);
		map.put("/apple-touch-icon-60x60.png", faviconRequestHandler);
		map.put("/apple-touch-icon-72x72.png", faviconRequestHandler);
		map.put("/apple-touch-icon-76x76.png", faviconRequestHandler);
		map.put("/apple-touch-icon-114x114.png", faviconRequestHandler);
		map.put("/apple-touch-icon-120x120.png", faviconRequestHandler);
		map.put("/apple-touch-icon-144x144.png", faviconRequestHandler);
		map.put("/apple-touch-icon-152x152.png", faviconRequestHandler);
		map.put("/apple-touch-icon-180x180.png", faviconRequestHandler);
		map.put("/apple-touch-icon-precomposed.png", faviconRequestHandler);
		map.put("/apple-touch-icon-57x57-precomposed.png", faviconRequestHandler);
		map.put("/apple-touch-icon-60x60-precomposed.png", faviconRequestHandler);
		map.put("/apple-touch-icon-72x72-precomposed.png", faviconRequestHandler);
		map.put("/apple-touch-icon-76x76-precomposed.png", faviconRequestHandler);
		map.put("/apple-touch-icon-114x114-precomposed.png", faviconRequestHandler);
		map.put("/apple-touch-icon-120x120-precomposed.png", faviconRequestHandler);
		map.put("/apple-touch-icon-144x144-precomposed.png", faviconRequestHandler);
		map.put("/apple-touch-icon-152x152-precomposed.png", faviconRequestHandler);
		map.put("/apple-touch-icon-180x180-precomposed.png", faviconRequestHandler);
		map.put("/safari-pinned-tab.svg", faviconRequestHandler);

		// android
		map.put("/site.webmanifest", faviconRequestHandler);
		map.put("/android-chrome-36x36.png", faviconRequestHandler);
		map.put("/android-chrome-48x48.png", faviconRequestHandler);
		map.put("/android-chrome-72x72.png", faviconRequestHandler);
		map.put("/android-chrome-96x96.png", faviconRequestHandler);
		map.put("/android-chrome-144x144.png", faviconRequestHandler);
		map.put("/android-chrome-192x192.png", faviconRequestHandler);
		map.put("/android-chrome-256x256.png", faviconRequestHandler);
		map.put("/android-chrome-384x384.png", faviconRequestHandler);
		map.put("/android-chrome-512x512.png", faviconRequestHandler);

		// ms
		map.put("/browserconfig.xml", faviconRequestHandler);
		map.put("/mstile-70x70.png", faviconRequestHandler);
		map.put("/mstile-150x150.png", faviconRequestHandler);
		map.put("/mstile-310x310.png", faviconRequestHandler);
		map.put("/mstile-310x150.png", faviconRequestHandler);

		mapping.setOrder(Integer.MIN_VALUE);
		mapping.setUrlMap(map);

		return mapping;
	}

	@Bean
	protected ResourceHttpRequestHandler faviconRequestHandler() {
		ResourceHttpRequestHandler requestHandler = new ResourceHttpRequestHandler();
		ClassPathResource classPathResource = new ClassPathResource("static/favicon/");
		List<Resource> locations = Arrays.asList(classPathResource);
		requestHandler.setLocations(locations);

		return requestHandler;
	}

}
