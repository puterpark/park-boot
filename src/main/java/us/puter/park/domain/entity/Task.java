package us.puter.park.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "taskUid")
	private Long taskUid;

	@Column(name = "taskName")
	private String taskName;

	@Column(name = "isUse")
	private Long isUse;

	@Column(name = "isAlive")
	private Long isAlive;

	@Column(name = "startDate")
	private LocalDateTime startDate;

	@Column(name = "endDate")
	private LocalDateTime endDate;

	@Builder
	public Task(String taskName, Long isUse, Long isAlive, LocalDateTime startDate, LocalDateTime endDate) {
		this.taskName = taskName;
		this.isUse = isUse;
		this.isAlive = isAlive;
		this.startDate = startDate;
		this.endDate = endDate;
	}

}
