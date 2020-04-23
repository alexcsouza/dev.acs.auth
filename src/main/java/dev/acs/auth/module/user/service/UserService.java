package dev.acs.auth.module.user.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.acs.auth.module.login.LoginDTO;
import dev.acs.auth.module.login.TokenAuthenticationService;
import dev.acs.auth.module.user.persistence.IUserRepository;
import dev.acs.auth.module.user.persistence.User;
import dev.acs.auth.module.user.security.CustomUserDetails;
import dev.acs.auth.module.user.service.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Qualifier("UserService")
@Slf4j
public class UserService implements IUserService, UserDetailsService {

	@Autowired
	private IUserRepository userRepository;

//	@Autowired
//	private JWTTokenUtil jwtTokenUtil;

	@Override
	public UserDTO getUser(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent()){
			throw new EntityNotFoundException();
		}
		User user = userOptional.get();
		ObjectMapper om = new ObjectMapper();
		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		return om.convertValue(user, UserDTO.class);
	}

	@Override
	public UserDTO getUser(String email) {
		Optional<User> userOptional = userRepository.findByEmail(email);
		if(!userOptional.isPresent()){
			throw new EntityNotFoundException();
		}
		User user = userOptional.get();
		ObjectMapper om = new ObjectMapper();
		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		return om.convertValue(user, UserDTO.class);
	}

	@Override
	public List<UserDTO> getList() {
		List<User> list = userRepository.findAll();
		ObjectMapper om = new ObjectMapper();
		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		return om.convertValue(list, new TypeReference<List<UserDTO>>(){});
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
		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		User user = om.convertValue(userDTO, User.class);
		user = userRepository.save(user);
		userDTO.setId(user.getId());

		return userDTO;

	}

	@Override
	public String authenticate(LoginDTO loginData) {

		/**
		 *
		 * TODO: Make token use salt and hashed password storage
		 *
		 *

		User user = userRepository.findByEmail(loginData.getUserName()).orElseThrow(()->new UsernameNotFoundException(""));
		StringBuilder stringBuilder = new StringBuilder().append(user.getName())
				.append(user.getPassword())
				.append(SECRET_HASH);

		byte[] genPass = DigestUtils.md5Digest(stringBuilder.toString().getBytes());
		byte[] logPass = loginData.getPassword().getBytes();

		if(genPass == logPass){
			jwtTokenUtil.generateToken(CustomUserDetails.builder().user(user).build());
		}

		*/
		return TokenAuthenticationService.addAuthentication(loadUserByUsername(loginData.getUsername()));

	}

	@Override
	public UserDetails loadUserByUsername(String email){
			User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("No user identifyied by %s", email)));
			user.setPassword(new BCryptPasswordEncoder(16).encode(user.getPassword()));
			return CustomUserDetails.builder().user(user).build();
	}

}
