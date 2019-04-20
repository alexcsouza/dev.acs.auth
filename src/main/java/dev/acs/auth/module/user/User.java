package dev.acs.auth.module.user;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import dev.acs.auth.core.persistence.PersistentEntity;
import dev.acs.auth.module.usergroup.UserGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User extends PersistentEntity {

	private static final long serialVersionUID = 1765930670583830015L;

	private String name;
	
	@ManyToMany
	private List<UserGroup> userGroup;
	
}
