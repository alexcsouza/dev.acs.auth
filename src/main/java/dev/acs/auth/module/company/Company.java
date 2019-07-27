package dev.acs.auth.module.company;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import dev.acs.auth.core.persistence.PersistentEntity;
import dev.acs.auth.module.user.persistence.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Entity
@EqualsAndHashCode(callSuper = false)
public class Company extends PersistentEntity {

	private static final long serialVersionUID = -1132020693411357129L;
	
	private String name;

	private String site;

	@ManyToMany
	private List<User> userList;
	
	
}
