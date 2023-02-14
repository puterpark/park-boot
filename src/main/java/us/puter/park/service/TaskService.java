package us.puter.park.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.puter.park.domain.entity.Task;
import us.puter.park.repository.TaskRepository;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TaskService {

	private final TaskRepository taskRepository;

	/**
	 * 스케줄러 존재 여부 확인
	 * @param taskName
	 * @return
	 */
	public boolean existsTaskByTaskName(String taskName) {

		return taskRepository.existsTaskByTaskName(taskName);
	}

	/**
	 * taskName을 통한 스케줄러 데이터 조회
	 * @param taskName
	 * @return
	 */
	public Task getTaskByTaskName(String taskName) {

		return taskRepository.findTaskByTaskName(taskName);
	}

	/**
	 * 스케줄러 정보 삽입
	 * @param task
	 * @return
	 */
	public Task insertTask(Task task) {

		return taskRepository.save(task);
	}

	/**
	 * 스케줄러 실행 처리 : 스케줄러 시작일시, 실행여부(실행중) 수정
	 * @param task
	 * @param startDate
	 */
	public void startTask(Task task, LocalDateTime startDate) {
		task.setStartDate(startDate);
		task.setIsAlive(1L);
		taskRepository.save(task);
	}

	/**
	 * 스케줄러 종료 처리 : 스케줄러 종료일시, 실행여부(정지) 수정
	 * @param task
	 * @param endDate
	 */
	public void endTask(Task task, LocalDateTime endDate) {
		task.setEndDate(endDate);
		task.setIsAlive(0L);
		taskRepository.save(task);
	}

	/**
	 * 스케줄러 초기화
	 * @param taskName
	 * @return
	 */
	public Task initTask(String taskName) {
		LocalDateTime startDate = LocalDateTime.now();

		// 스케줄러 존재 확인
		boolean existsTask = existsTaskByTaskName(taskName);

		Task task = null;
		if (existsTask) {
			// 스케줄러 정보 조회
			task = getTaskByTaskName(taskName);
		} else {
			// 스케줄러 정보 삽입
			task = Task.builder()
					.taskName(taskName)
					.isUse(1L)
					.isAlive(0L)
					.startDate(LocalDateTime.of(1970, 1, 1, 9, 0))
					.endDate(LocalDateTime.of(1970, 1, 1, 9, 0))
					.build();
			insertTask(task);
		}

		// 스케줄러의 사용여부 및 실행여부 확인
		if (task.getIsUse() == 0 || task.getIsAlive() == 1) {
			log.info("[" + taskName + "] is not used, Check the setting.");
			return null;
		}

		// 스케줄러 실행 처리
		startTask(task, startDate);

		return task;
	}

}
