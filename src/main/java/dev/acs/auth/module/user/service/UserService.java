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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

	private String emailRegex = "[a-z0-9.]+@[a-z0-9]+.[a-z]+\\.([a-z]+)";
	
	// TODO: export to validation.properties
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
	
//		TODO: Before implement confirmation email, configure logs and environments for safety add SMTP data into application.properties
//	@Autowired
//	private UserEmailSend userEmailSend;
	
	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;
	
	@Autowired
	private ModelMapperBean modelMapperBean;
	
	@Value("${auth.aurhentication.confirmation-required-to-authenticate:true}")
	private boolean confirmationRequiredToAuthenticate;
	
	public UserService(IUserRepository userRepository, TokenAuthenticationService tokenAuthService, ModelMapperBean modelMapperBean) {
		super();
		this.tokenAuthenticationService = tokenAuthService;
		this.userRepository = userRepository;
		this.modelMapperBean = modelMapperBean;
	}
	
	@Override
	public UserDTO get(Long id) {
	
		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent()){
			throw new EntityNotFoundException();
		}
		User user = userOptional.get();
		user.setPassword(null);
		return modelMapperBean.getModelMapper().map(user, UserDTO.class);
		
	}

	@Override
	public UserDTO get(String email) {
		Optional<User> userOptional = userRepository.findByEmail(email);
		if(!userOptional.isPresent()){
			throw new EntityNotFoundException();
		}
		User user = userOptional.get();
		user.setPassword(null);
		return modelMapperBean.getModelMapper().map(user, UserDTO.class);
	}

	@Override
	public Page<UserDTO> getList(Pageable pageable) {
		Page<User> list = userRepository.findAll(pageable);
		List<UserDTO> dtoList = list.stream()
			.map(
					user -> {
						user.setPassword(null);
						return modelMapperBean.getModelMapper().map(user, UserDTO.class);
					}
			)
			.collect(Collectors.toList());
		return new PageImpl<>(dtoList, pageable, list.getTotalElements());  
	}

	@Override
	public UserDTO registerUser(UserDTO userDTO) {

		if(userDTO.getId() != null){
			// TODO: Load string from messages.properties
			throw new IllegalArgumentException("User already registered");
		}

		if(userRepository.findByEmail(userDTO.getEmail()).isPresent()){
			// TODO: Load string from messages.properties
			throw new IllegalArgumentException("User already registered");
		}
		
		validateEmail(userDTO.getEmail());
		validatePassword(userDTO.getPassword());

		User user = modelMapperBean.getModelMapper().map(userDTO, User.class);
		user.setConfirmed(false);
		user.setPassword(encodePassord(user));
		user = userRepository.save(user);
		
// 		TODO: Before implement confirmation email, configure logs and environments for safety add SMTP data into application.properties
//		userEmailSend.sendRegistryConfirmationEmail(user);
		
		userDTO.setId(user.getId());
		userDTO.setPassword(null);
		return userDTO;

	}

	// TODO: delegate to validation service
	private void validateEmail(String email) {
		if(!Pattern.matches(emailRegex, email)) {
			throw new IllegalArgumentException("Invalid format for email.");
		}
	}

	// TODO: delegate to password service
	private String encodePassord(User user) {
		return new BCryptPasswordEncoder(16).encode(user.getPassword());
	}

	// TODO: delegate to password service
	private void validatePassword(String password) {
		
		// TODO: Make it more flexible indication the pattern that failed
		List<String> fail = 
			passwordRegex
				.stream()
				.filter(regex -> ! Pattern.matches(regex, password))
				.collect(Collectors.toList());
				
		if(!fail.isEmpty()) {
			// TODO: Load string from messages.properties
			throw new IllegalArgumentException("Invalid format for passord.");
		}
		
	}

	// TODO: delegate to login service
	@Override
	public String authenticate(LoginDTO loginData) throws AuthenticationException {
		UserDetails userDetails = loadUserByUsername(loginData.getUsername());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
		boolean valid = encoder.matches(loginData.getPassword(), userDetails.getPassword());
		if(!valid) {
			// TODO: Load string from messages.properties
			throw new AuthenticationException("Invalid username or password");
		}
		return tokenAuthenticationService.addAuthentication(userDetails);
	}

	// TODO: delegate to login service
	@Override
	public UserDetails loadUserByUsername(String email){
		// TODO: Load string from messages.properties
		Optional<User> user = userRepository.findByEmail(email);
		User userObj = null;
		if( ! user.isPresent()) {
			userObj = userRepository.findByAlias(email)
					.orElseThrow(() ->new UsernameNotFoundException(String.format("No user identifyied by %s", email)));
		}else {
			userObj = user.get();
		}
		
		if(confirmationRequiredToAuthenticate && !userObj.getConfirmed().booleanValue()) {
			throw new UsernameNotFoundException("User not cofirmed.");
		}
		
		return CustomUserDetails.builder().user(userObj).build();
	}

}
