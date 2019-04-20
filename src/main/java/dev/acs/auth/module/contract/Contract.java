package dev.acs.auth.module.contract;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import dev.acs.auth.core.persistence.PersistentEntity;
import dev.acs.auth.module.client.Client;
import dev.acs.auth.module.software.Software;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Contract extends PersistentEntity{

	private static final long serialVersionUID = 276185490264488580L;

	@ManyToOne
	private Software software;
	
	@ManyToOne
	private Client client;

	
}
