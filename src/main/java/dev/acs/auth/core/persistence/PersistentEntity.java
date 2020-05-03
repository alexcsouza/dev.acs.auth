package dev.acs.auth.core.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class PersistentEntity implements Serializable{

	private static final long serialVersionUID = 7512033845966917279L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;

	@Version
	@Temporal(TemporalType.TIMESTAMP)
	private Date version;

	private Date lastUpdateDate;

	private Date creationDate;

	@PrePersist
	private void onInsert() {
		this.creationDate = new Date();
		this.lastUpdateDate = this.creationDate;
	}

	@PreUpdate
    private void onUpdate() {
        this.lastUpdateDate = new Date();
    }

}
