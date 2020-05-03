package dev.acs.auth.module.contract;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;

import dev.acs.auth.core.persistence.PersistentEntity;
import dev.acs.auth.module.app.App;
import dev.acs.auth.module.company.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@EqualsAndHashCode(callSuper = false)
@Audited
public class Contract extends PersistentEntity{

	private static final long serialVersionUID = 276185490264488580L;

	@ManyToOne
	private App app;
	
	@ManyToOne
	private Company company;

	
}
