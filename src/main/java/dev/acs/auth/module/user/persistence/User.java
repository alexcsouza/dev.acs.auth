package dev.acs.auth.module.user.persistence;

import dev.acs.auth.core.persistence.PersistentEntity;
import dev.acs.auth.module.usergroup.UserGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
// workaround to use the keyword 'user' as table name
@Table(name = "`user`")
public class User extends PersistentEntity {

	private static final long serialVersionUID = 1765930670583830015L;

	private String name;

	private String email;

	private String password;

	@ManyToMany
	@JoinTable(name = "user_group_user",
		joinColumns = { @JoinColumn(name = "user_id") },
		inverseJoinColumns = { @JoinColumn(name = "user_group_id") })
	// workaround to prevent foreign key to be created
	//@org.hibernate.annotations.ForeignKey(name = "none")
	private List<UserGroup> userGroups;
	
}
