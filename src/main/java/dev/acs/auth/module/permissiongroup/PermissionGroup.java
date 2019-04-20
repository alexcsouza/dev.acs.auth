package dev.acs.auth.module.permissiongroup;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import dev.acs.auth.core.persistence.PersistentEntity;
import dev.acs.auth.module.permission.Permission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PermissionGroup extends PersistentEntity {

	private static final long serialVersionUID = -609188872254658857L;

	private String name;
	private String description;
	
	@ManyToMany
	private List<Permission> permissionList;
	
}
