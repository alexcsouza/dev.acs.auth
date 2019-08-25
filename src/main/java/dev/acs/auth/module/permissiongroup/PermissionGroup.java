package dev.acs.auth.module.permissiongroup;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import dev.acs.auth.core.persistence.PersistentEntity;
import dev.acs.auth.module.permission.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@EqualsAndHashCode(callSuper = false)
@Audited
public class PermissionGroup extends PersistentEntity {

	private static final long serialVersionUID = -609188872254658857L;

	private String name;
	private String description;
	
	@ManyToMany
	private List<Permission> permissionList;
	
}
