package us.puter.park.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.puter.park.domain.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long>{

}
