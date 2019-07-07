package dev.acs.auth.module.software;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import dev.acs.auth.core.persistence.PersistentEntity;
import dev.acs.auth.module.contract.Contract;
import dev.acs.auth.module.module.Module;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Software extends PersistentEntity{

	private static final long serialVersionUID = 3604770249429501407L;
	private String name;
	private String Description;
	private String label;
	
	@ManyToMany
	private List<Contract> contractList;
	
	@OneToMany
	private List<Module> moduleList;
	
}
