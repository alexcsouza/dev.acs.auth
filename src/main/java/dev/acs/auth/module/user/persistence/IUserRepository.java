package dev.acs.auth.module.user.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends CrudRepository<User, Long> {
    @Override
    public List<User> findAll();

    @Query(value = "from User u where u.email=:email")
    public Optional<User> findByEmail(@Param(value="email") String email);
    
}