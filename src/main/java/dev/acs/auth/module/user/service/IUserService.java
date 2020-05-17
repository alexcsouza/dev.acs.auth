package dev.acs.auth.module.user.service;

import javax.security.sasl.AuthenticationException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.acs.auth.module.login.LoginDTO;
import dev.acs.auth.module.user.service.dto.UserDTO;

@Service
public interface IUserService{

	UserDTO get(Long id);

	UserDTO get(String email);

	UserDTO registerUser(UserDTO userDTO);

    String authenticate(LoginDTO userName) throws AuthenticationException;

	Page<UserDTO> getList(Pageable pageable);
}
