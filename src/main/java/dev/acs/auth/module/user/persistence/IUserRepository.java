package dev.acs.auth.module.user.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends PagingAndSortingRepository<User, Long> {
    
    @Query(value = "from User u where u.email=:email")
    Optional<User> findByEmail(@Param(value="email") String email);

    @Query(value = "from User u where u.alias=:alias")
    Optional<User> findByAlias(String alias);
    
}