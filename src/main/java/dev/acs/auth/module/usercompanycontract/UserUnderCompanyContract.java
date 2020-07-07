package dev.acs.auth.module.usercompanycontract;

import java.util.List;

import javax.persistence.Entity;

import org.hibernate.envers.Audited;

import dev.acs.auth.core.persistence.PersistentEntity;
import dev.acs.auth.module.company.Company;
import dev.acs.auth.module.contract.Contract;
import dev.acs.auth.module.permission.Permission;
import dev.acs.auth.module.user.persistence.User;
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
@EqualsAndHashCode(callSuper = false)
@Audited
public class UserUnderCompanyContract extends PersistentEntity {

	private static final long serialVersionUID = 1766035390133783383L;
	
	private User user;
	
	private Company company;
	 
	private Contract contract;
	
	private List<Permission> permissions;

}
