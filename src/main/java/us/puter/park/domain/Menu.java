package us.puter.park.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "menu")
public class Menu {

	@Id
	private Long menuUid;

	@Column
	private String name;

	@Column
	private String mode;

	@Column
	private String uri;

	@Column
	private String icon;

	@Transient
	private Integer activeFlag = 0;

	public Long getMenuUid() {
		return menuUid;
	}

	public void setMenuUid(Long menuUid) {
		this.menuUid = menuUid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}
}
