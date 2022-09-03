package us.puter.park.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import us.puter.park.domain.entity.Menu;
import us.puter.park.repository.MenuRedisRepository;
import us.puter.park.repository.MenuRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {

	private final MenuRepository menuRepository;

	private final MenuRedisRepository menuRedisRepository;

	public Model setModelFromMenu(Model model, String mode) {

		// redis에서 먼저 메뉴 목록을 찾는다.
		List<Menu> menuList = menuRedisRepository.findMenuList();

		// redis에서 가져온 값이 없을 경우 DB에서 찾는다.
		if (CollectionUtils.isEmpty(menuList)) {
			long menuListCount = menuRepository.countMenuByUseFlag(1L);

			if (menuListCount > 0) {
				menuList = menuRepository.findMenuByUseFlag(1L);
				menuRedisRepository.cacheMenuList(menuList); // 캐시 저장
			}
		}

		/*
		 * mode에 값이 있을 때
		 * menuList를 반복문으로 돌려서 mode 값이 일치할 경우
		 * active class 적용을 위하여 activeFlag를 1로 지정
		 */
		if (mode != null) {
			for (Menu menu : menuList) {
				if (mode.equals(menu.getMode())) {
					menu.setActiveFlag(1);
					break;
				}
			}
		}

		model.addAttribute("menuList", menuList);

		return model;
	}

}
