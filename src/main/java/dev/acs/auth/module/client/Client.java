package dev.acs.auth.module.client;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import dev.acs.auth.core.persistence.PersistentEntity;
import dev.acs.auth.module.user.User;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Entity
public class Client extends PersistentEntity {

	private static final long serialVersionUID = -1132020693411357129L;
	
	private String name;

	@ManyToMany
	private List<User> userList;
	
	
}
