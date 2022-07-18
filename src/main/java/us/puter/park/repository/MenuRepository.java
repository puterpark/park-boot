package us.puter.park.repository;

import us.puter.park.domain.Menu;

import java.util.List;

public interface MenuRepository {

	long count();

	List<Menu> findAll();

}
