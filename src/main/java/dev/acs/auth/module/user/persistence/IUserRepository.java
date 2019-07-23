package dev.acs.auth.module.user.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();

    @Query(value = "from User u where u.email=:email")
    Optional<User> findByEmail(@Param(value="email") String email);
    
}