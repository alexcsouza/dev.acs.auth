package dev.acs.auth.module.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.StringUtils;
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

@Service
@Qualifier("UserService")
public class UserService implements IUserService, UserDetailsService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;
	
//	@Autowired
//	private JWTTokenUtil jwtTokenUtil;


	public UserService(IUserRepository userRepository, TokenAuthenticationService tokenAuthService) {
		super();
		this.tokenAuthenticationService = tokenAuthService;
		this.userRepository = userRepository;
	}
	
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
		
		validatePassword(userDTO.getPassword());

		ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		User user = om.convertValue(userDTO, User.class);
		user.setPassword(encodePassord(user));
		user = userRepository.save(user);
		userDTO.setId(user.getId());

		return userDTO;

	}
	
	private String encodePassord(User user) {
		return new BCryptPasswordEncoder(16).encode(user.getPassword());
	}

	private void validatePassword(String password) {
		List<String> errors = new ArrayList<>();
		
		if(password.length() < 8) {
			errors.add("Password must be greater then 8 characters.");
		}
		
		if(password.toUpperCase().equals(password) || password.toLowerCase().equals(password) ) {
			errors.add("Password shoud have bouth, upper and lower case characters.");
		}
		
		if(!password.matches("/\\W/g")) {
			errors.add("Password shoud have at least one special character.");
		}
		
		if(!errors.isEmpty()) {
			throw new IllegalArgumentException(String.format("Invalid format for passord: %s", StringUtils.join(errors, "\n - ")));
		}
		
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
		return tokenAuthenticationService.addAuthentication(
				loadUserByUsername(loginData.getUsername())
			);

	}

	@Override
	public UserDetails loadUserByUsername(String email){
		User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("No user identifyied by %s", email)));
		user.setPassword(encodePassord(user));
		return CustomUserDetails.builder().user(user).build();
	}

	

}
