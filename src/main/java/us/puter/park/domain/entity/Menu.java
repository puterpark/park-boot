package us.puter.park.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "menu")
@Getter
@Setter
public class Menu implements Serializable {

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
