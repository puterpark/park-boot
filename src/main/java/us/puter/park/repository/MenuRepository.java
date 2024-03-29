package us.puter.park.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.puter.park.domain.entity.Menu;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

	/**
	 * 사용여부에 따른 메뉴 존재 여부
	 * @param useFlag
	 * @return
	 */
	boolean existsMenuByUseFlag(Long useFlag);

	/**
	 * 사용여부에 따른 메뉴 목록
	 *
	 * @param useFlag
	 * @return
	 */
	List<Menu> findMenuByUseFlag(Long useFlag);
}
