package us.puter.park.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import us.puter.park.domain.entity.Menu;
import us.puter.park.repository.MenuRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {

	private final MenuRepository menuRepository;

	public Model setModelFromMenu(Model model, String mode) {

		long menuListCount = menuRepository.countMenuByUseFlag(1L);
		if (menuListCount > 0) {
			List<Menu> menuList = menuRepository.findMenuByUseFlag(1L);

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
		}

		return model;
	}

}
