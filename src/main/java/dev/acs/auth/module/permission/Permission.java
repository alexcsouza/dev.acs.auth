package dev.acs.auth.module.permission;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import dev.acs.auth.core.persistence.PersistentEntity;
import dev.acs.auth.module.feature.Feature;
import dev.acs.auth.module.permissiongroup.PermissionGroup;
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
public class Permission extends PersistentEntity {

	private static final long serialVersionUID = -6708078788530807565L;
	
	private String name;
	private String description;
	private String label;

	@ManyToMany
	private List<PermissionGroup> permissionGroupList;
	
	@OneToOne
	private Feature feature;

}
