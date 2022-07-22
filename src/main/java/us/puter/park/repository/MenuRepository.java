package us.puter.park.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.puter.park.domain.entity.Menu;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long>{

	Long countMenuByUseFlag(Long useFlag);

	List<Menu> findMenuByUseFlag(Long useFlag);
}
