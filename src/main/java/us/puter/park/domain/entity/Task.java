package us.puter.park.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

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
	private Long startDate;

	@Column(name = "endDate")
	private Long endDate;

	@Builder
	public Task(String taskName, Long isUse, Long isAlive, Long startDate, Long endDate) {
		this.taskName = taskName;
		this.isUse = isUse;
		this.isAlive = isAlive;
		this.startDate = startDate;
		this.endDate = endDate;
	}

}
