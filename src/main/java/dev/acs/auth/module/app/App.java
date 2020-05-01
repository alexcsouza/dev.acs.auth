package dev.acs.auth.module.app;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.envers.Audited;

import dev.acs.auth.core.persistence.PersistentEntity;
import dev.acs.auth.module.contract.Contract;
import dev.acs.auth.module.module.Module;
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
public class App extends PersistentEntity{

	private static final long serialVersionUID = 3604770249429501407L;
	private String name;
	private String label;
	private String description;

	@ManyToMany
	private List<Contract> contractList;
	
	@OneToMany
	private List<Module> moduleList;
	
}
