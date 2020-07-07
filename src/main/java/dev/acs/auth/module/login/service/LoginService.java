package dev.acs.auth.module.login.service;

import java.util.Optional;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.acs.auth.module.login.dto.LoginDTO;
import dev.acs.auth.module.user.persistence.IUserRepository;
import dev.acs.auth.module.user.persistence.User;
import dev.acs.auth.module.user.security.CustomUserDetails;

@Service
@Qualifier("LoginService")
public class LoginService implements ILoginService, UserDetailsService {

	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;

	@Autowired
	private IUserRepository userRepository;

	@Value("${auth.aurhentication.confirmation-required-to-authenticate:true}")
	private boolean confirmationRequiredToAuthenticate;

	public LoginService() {
	}

	public LoginService(TokenAuthenticationService tokenAuthenticationService, IUserRepository userRepository,
			boolean confirmationRequiredToAuthenticate) {
		super();
		this.tokenAuthenticationService = tokenAuthenticationService;
		this.userRepository = userRepository;
		this.confirmationRequiredToAuthenticate = confirmationRequiredToAuthenticate;
	}

	@Override
	public String authenticate(LoginDTO loginData) throws AuthenticationException {
		UserDetails userDetails = loadUserByUsername(loginData.getUsername());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		boolean valid = encoder.matches(loginData.getPassword(), userDetails.getPassword());
		if (!valid) {
			// TODO: Load string from messages.properties
			throw new AuthenticationException("Invalid username or password");
		}
		return tokenAuthenticationService.addAuthentication(userDetails);
	}

	@Override
	public UserDetails loadUserByUsername(String email) {
		// TODO: Load string from messages.properties
		Optional<User> user = userRepository.findByEmail(email);
		User userObj = null;
		if (!user.isPresent()) {
			userObj = userRepository.findByAlias(email).orElseThrow(
					() -> new UsernameNotFoundException(String.format("No user identifyied by %s", email)));
		} else {
			userObj = user.get();
		}

		if (confirmationRequiredToAuthenticate && !userObj.getConfirmed().booleanValue()) {
			throw new UsernameNotFoundException("User not cofirmed.");
		}

		return CustomUserDetails.builder().user(userObj).build();
	}
}
