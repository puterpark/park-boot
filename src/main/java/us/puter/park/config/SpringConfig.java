package us.puter.park.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.puter.park.repository.MenuRepository;
import us.puter.park.service.MenuService;

@Configuration
public class SpringConfig {

	private final MenuRepository menuRepository;

	public SpringConfig(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	@Bean
	public MenuService menuService() {
		return new MenuService(menuRepository);
	}

}
