package dev.acs.auth.module.user.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.acs.auth.core.bean.ModelMapperBean;
import dev.acs.auth.module.login.LoginDTO;
import dev.acs.auth.module.login.TokenAuthenticationService;
import dev.acs.auth.module.user.persistence.IUserRepository;
import dev.acs.auth.module.user.persistence.User;
import dev.acs.auth.module.user.security.CustomUserDetails;
import dev.acs.auth.module.user.service.dto.UserDTO;

@Service
@Qualifier("UserService")
public class UserService implements IUserService, UserDetailsService {

	private List<String> passwordRegex = Arrays.asList(
			// The string must contain at least 1 lowercase alphabetical character
			".*[a-z].*",
			
			// The string must contain at least 1 uppercase alphabetical character
			".*[A-Z].*",
			
			// The string must contain at least 1 numeric character
			".*[0-9].*", 		
			
			// The string must contain at least one special character, but we are escaping reserved RegEx characters to avoid conflict
			".*[!@#$%^&*~?\\\\\\]\\[\\}\\{\\=\\+\\-\\/\\.\\(\\)].*",
			
			// The string must be eight characters or longer
			".{8,}"
		);
	
	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;
	
	@Autowired
	private ModelMapperBean modelMapperBean;
	
	public UserService(IUserRepository userRepository, TokenAuthenticationService tokenAuthService, ModelMapperBean modelMapperBean) {
		super();
		this.tokenAuthenticationService = tokenAuthService;
		this.userRepository = userRepository;
		this.modelMapperBean = modelMapperBean;
	}
	
	@Override
	public UserDTO getUser(Long id) {
	
		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent()){
			throw new EntityNotFoundException();
		}
		User user = userOptional.get();
		return modelMapperBean.getModelMapper().map(user, UserDTO.class);
		
	}

	@Override
	public UserDTO getUser(String email) {
		Optional<User> userOptional = userRepository.findByEmail(email);
		if(!userOptional.isPresent()){
			throw new EntityNotFoundException();
		}
		User user = userOptional.get();
		return modelMapperBean.getModelMapper().map(user, UserDTO.class);
	}

	@Override
	public List<UserDTO> getList() {
		List<User> list = userRepository.findAll();
		return list.stream().map(u -> modelMapperBean.getModelMapper().map(u, UserDTO.class)).collect(Collectors.toList());
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

		User user = modelMapperBean.getModelMapper().map(userDTO, User.class);
		user.setPassword(encodePassord(user));
		user = userRepository.save(user);
		userDTO.setId(user.getId());
		userDTO.setPassword(null);
		return userDTO;

	}
	
	private String encodePassord(User user) {
		return new BCryptPasswordEncoder(16).encode(user.getPassword());
	}

	private void validatePassword(String password) {
		
		// TODO: Make it more flexible indication the pattern that failed
		List<String> fail = 
			passwordRegex
				.stream()
				.filter(regex -> ! Pattern.matches(regex, password))
				.collect(Collectors.toList());
				
		if(!fail.isEmpty()) {
			throw new IllegalArgumentException("Invalid format for passord.");
		}
		
	}

	@Override
	public String authenticate(LoginDTO loginData) throws AuthenticationException {

		UserDetails userDetails = loadUserByUsername(loginData.getUsername());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
		boolean valid = encoder.matches(loginData.getPassword(), userDetails.getPassword());
		if(!valid) {
			throw new AuthenticationException("Invalid username or password");
		}
		return tokenAuthenticationService.addAuthentication(userDetails);

	}

	@Override
	public UserDetails loadUserByUsername(String email){
		User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("No user identifyied by %s", email)));
		//user.setPassword(encodePassord(user));
		return CustomUserDetails.builder().user(user).build();
	}

	

}
