package us.puter.park.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.puter.park.domain.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long>{

}
