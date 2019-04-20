package dev.acs.auth.module.module;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import dev.acs.auth.core.persistence.PersistentEntity;
import dev.acs.auth.module.functionality.Functionality;
import dev.acs.auth.module.software.Software;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Module extends PersistentEntity {

	private static final long serialVersionUID = 6795962897526642148L;

	private String name;
	private String description;
	private String label;

	@OneToMany
	private List<Functionality> functionalityList;
	
	@ManyToOne
	private Software software;

}
