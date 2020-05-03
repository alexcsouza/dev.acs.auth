package dev.acs.auth.module.module;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.envers.Audited;

import dev.acs.auth.core.persistence.PersistentEntity;
import dev.acs.auth.module.app.App;
import dev.acs.auth.module.feature.Feature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@EqualsAndHashCode(callSuper = true)
@Audited
public class Module extends PersistentEntity {

	private static final long serialVersionUID = 6795962897526642148L;

	private String name;
	private String description;
	private String label;

	@OneToMany
	private List<Feature> featureList;
	
	@ManyToOne
	private App app;

}
