package dev.acs.auth.module.user.persistence;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import dev.acs.auth.core.persistence.PersistentEntity;
import dev.acs.auth.module.usergroup.UserGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
	
	@ManyToMany
	@JoinTable(name = "user_group_user",
		joinColumns = { @JoinColumn(name = "user_id") },
		inverseJoinColumns = { @JoinColumn(name = "user_group_id") })
	// workaround to prevent foreign key to be created
	//@org.hibernate.annotations.ForeignKey(name = "none")
	private List<UserGroup> userGroups;
	
}