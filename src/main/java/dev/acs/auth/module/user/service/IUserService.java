package dev.acs.auth.module.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.acs.auth.core.exception.RowNotFound;
import dev.acs.auth.module.user.dto.UserDTO;

@Service
public interface IUserService{

	UserDTO get(Long id) throws RowNotFound;

	UserDTO get(String email) throws RowNotFound;

	UserDTO registerUser(UserDTO userDTO);

	Page<UserDTO> getList(Pageable pageable) throws RowNotFound;
	
	UserDTO save(UserDTO userDTO);
}
