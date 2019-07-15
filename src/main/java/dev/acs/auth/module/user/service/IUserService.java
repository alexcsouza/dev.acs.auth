package dev.acs.auth.module.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.acs.auth.module.user.service.dto.UserDTO;

@Service
public interface IUserService{

	public UserDTO getUser(Long id);

	public List<UserDTO> getList();

	public UserDTO registerUser(UserDTO userDTO);

}
