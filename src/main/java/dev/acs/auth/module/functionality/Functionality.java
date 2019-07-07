package dev.acs.auth.module.functionality;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import dev.acs.auth.core.persistence.PersistentEntity;
import dev.acs.auth.module.module.Module;
import dev.acs.auth.module.permission.Permission;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
public class Functionality extends PersistentEntity {

	private static final long serialVersionUID = 2522715322138763173L;
	private String name;
	private String description;
	private String label;

	@ManyToOne
	private Module module;

	@OneToOne
	private Permission permission;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

}
