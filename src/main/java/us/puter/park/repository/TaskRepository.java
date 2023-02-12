package us.puter.park.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.puter.park.domain.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

	/**
	 * 스케줄러 존재 여부 확인
	 * @param taskName
	 * @return
	 */
	boolean existsTaskByTaskName(String taskName);

	/**
	 * taskName을 통한 스케줄러 데이터 확인
	 * @param taskName
	 * @return
	 */
	Task findTaskByTaskName(String taskName);

}
