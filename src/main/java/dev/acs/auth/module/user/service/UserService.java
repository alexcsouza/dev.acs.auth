package dev.acs.auth.module.user.service;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.acs.auth.module.user.persistence.IUserRepository;
import dev.acs.auth.module.user.persistence.User;
import dev.acs.auth.module.user.security.CustomUserDetails;
import dev.acs.auth.module.user.service.dto.UserDTO;

@Service
public class UserService implements IUserService, UserDetailsService {

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
	public UserDTO getUser(String email) {
		User user = userRepository.findByEmail(email).get();
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(username);
		if(! user.isPresent()){
			throw new UsernameNotFoundException("User name not found");
		}
		return CustomUserDetails.builder().user(user.get()).build();
	}

}
