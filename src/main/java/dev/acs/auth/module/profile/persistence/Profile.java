package dev.acs.auth.module.profile.persistence;

import dev.acs.auth.core.persistence.PersistentEntity;
import dev.acs.auth.module.user.persistence.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = false)
@Audited
public class Profile extends PersistentEntity {

    private static final long serialVersionUID = 1765930670583830015L;

    @OneToOne
    @JoinTable(name = "user_profile",
            joinColumns = { @JoinColumn(name = "profile_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private User user;

}
