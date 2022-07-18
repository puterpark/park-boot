package us.puter.park.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.puter.park.domain.Menu;

import java.util.List;

public interface JpaMenuRepository extends JpaRepository<Menu, Long>, MenuRepository{

	@Override
	long count();

	@Override
	List<Menu> findAll();

}
