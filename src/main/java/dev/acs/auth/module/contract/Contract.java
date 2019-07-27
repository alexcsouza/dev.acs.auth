package dev.acs.auth.module.contract;

import dev.acs.auth.core.persistence.PersistentEntity;
import dev.acs.auth.module.app.App;
import dev.acs.auth.module.company.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@EqualsAndHashCode(callSuper = false)
public class Contract extends PersistentEntity{

	private static final long serialVersionUID = 276185490264488580L;

	@ManyToOne
	private App app;
	
	@ManyToOne
	private Company company;

	
}
