package dev.acs.auth.module.usergroup;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import dev.acs.auth.core.persistence.PersistentEntity;
import dev.acs.auth.module.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode
@SuperBuilder
@Entity
public class UserGroup extends PersistentEntity {

	private static final long serialVersionUID = -5033866583673683706L;
	private String name;
	private String description;
	
	@ManyToMany(mappedBy = "userGroups")
	@org.hibernate.annotations.ForeignKey(name = "none")
	private List<User> userList;

	
}
