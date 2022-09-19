package us.puter.park.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "menu")
@Getter
@Setter
public class Menu {

	@Id
	@Column(name = "menuUid")
	private Long menuUid;

	@Column(name = "name")
	private String name;

	@Column(name = "mode")
	private String mode;

	@Column(name = "uri")
	private String uri;

	@Column(name = "icon")
	private String icon;

	@Column(name = "useFlag")
	private Long useFlag;

	@Transient
	private Integer activeFlag = 0;

}
