package dev.acs.auth.module.feature;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.envers.Audited;

import dev.acs.auth.core.persistence.PersistentEntity;
import dev.acs.auth.module.module.Module;
import dev.acs.auth.module.permission.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@Entity
@Audited
public class Feature extends PersistentEntity {

	private static final long serialVersionUID = 2522715322138763173L;
	private String name;
	private String description;
	private String label;

	@ManyToOne
	private Module module;

	@OneToMany
	private List<Permission> permissionsGranted;

}
