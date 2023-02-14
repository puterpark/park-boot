package us.puter.park.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import us.puter.park.domain.entity.Task;
import us.puter.park.service.TaskService;

import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class TestScheduler {

	private final TaskService taskService;

	private final String TASK_NAME = getClass().getSimpleName();

//	@Scheduled(fixedDelay = 5000)
	public void testTask() {

		Task task = taskService.initTask(TASK_NAME);
		if (task == null) {
			return;
		}

		log.info("hi!");

		// 스케줄러 종료 처리
		taskService.endTask(task, LocalDateTime.now());
	}

}
