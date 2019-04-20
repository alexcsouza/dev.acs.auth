package dev.acs.auth.module.user.service;

import org.springframework.data.repository.Repository;

import dev.acs.auth.module.user.User;


public interface IUserService extends Repository<User, Long> {

}
