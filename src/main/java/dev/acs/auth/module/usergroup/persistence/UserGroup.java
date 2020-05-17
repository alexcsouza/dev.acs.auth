package dev.acs.auth.module.usergroup.persistence;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.hibernate.envers.Audited;

import dev.acs.auth.core.persistence.PersistentEntity;
import dev.acs.auth.module.user.persistence.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Entity
@EqualsAndHashCode(callSuper = false)
@Audited
public class UserGroup extends PersistentEntity {

	private static final long serialVersionUID = -5033866583673683706L;
	private String name;
	private String description;
	
	@SuppressWarnings("deprecation")
	@ManyToMany(mappedBy = "userGroups")
	@org.hibernate.annotations.ForeignKey(name = "none")
	private List<User> userList;
	
}
