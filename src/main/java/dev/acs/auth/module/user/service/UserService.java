package dev.acs.auth.module.user.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.acs.auth.module.user.persistence.IUserRepository;
import dev.acs.auth.module.user.persistence.User;
import dev.acs.auth.module.user.service.dto.UserDTO;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public UserDTO getUser(Long id) {
		User user = userRepository.findById(id).get();
		ObjectMapper om = new ObjectMapper();
		UserDTO userDTO = om.convertValue(user, UserDTO.class);
		return userDTO;
	}

	@Override
	public List<UserDTO> getList() {
		List<User> list = userRepository.findAll();
		ObjectMapper om = new ObjectMapper();
		List<UserDTO> dtolist = om.convertValue(list, new TypeReference<List<UserDTO>>(){});
		return dtolist;
	}

	@Override
	public UserDTO registerUser(UserDTO userDTO) {

		if(userDTO.getId() != null){
			throw new IllegalArgumentException("User already registered");
		}

		if(userRepository.findByEmail(userDTO.getEmail()).isPresent()){
			throw new IllegalArgumentException("User already registered");
		}

		ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		User user = om.convertValue(userDTO, User.class);
		user = userRepository.save(user);
		userDTO.setId(user.getId());

		return userDTO;

	}

}
