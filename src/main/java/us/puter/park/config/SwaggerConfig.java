package us.puter.park.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {

	@Value("${spring.profiles.active}")
	private String profile;

	@Bean
	public OpenAPI openAPI(@Value("${application.version}") String appVersion) {
		Info info = new Info().title("Park API Documentation").version(appVersion)
				.description("잡동사니 공원")
				.contact(new Contact().name("Puter").email("puterpark@me.com"));

		String serviceUrl = "";
		List<Server> serverList = new ArrayList<>();
		if ("local".equals(profile)){
			serviceUrl = "http://127.0.0.1:8080";
		} else if ("live".equals(profile)) {
			serviceUrl = "https://puter.us";
		}
		serverList.add(new Server().url(serviceUrl));

		return new OpenAPI()
				.servers(serverList)
				.components(new Components())
				.info(info);
	}
}
