package dev.acs.auth.module.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.acs.auth.module.login.LoginDTO;
import dev.acs.auth.module.user.service.dto.UserDTO;

@Service
public interface IUserService{

	UserDTO getUser(Long id);

	UserDTO getUser(String email);

	List<UserDTO> getList();

	UserDTO registerUser(UserDTO userDTO);

    String authenticate(LoginDTO userName);
}
