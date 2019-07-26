package dev.acs.auth.module.user.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.acs.auth.core.security.JWTTokenUtil;
import dev.acs.auth.module.login.LoginDTO;
import dev.acs.auth.module.user.persistence.IUserRepository;
import dev.acs.auth.module.user.persistence.User;
import dev.acs.auth.module.user.security.CustomUserDetails;
import dev.acs.auth.module.user.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Collection;
import java.util.List;

@Service
public class UserService implements IUserService, UserDetailsService {

	private static final String SECRET_HASH = "ifPx;n%5BaZTR'y<2N,2$Ky?";

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private JWTTokenUtil jwtTokenUtil;

	@Override
	public UserDTO getUser(Long id) {
		User user = userRepository.findById(id).get();
		ObjectMapper om = new ObjectMapper();
		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		UserDTO userDTO = om.convertValue(user, UserDTO.class);
		return userDTO;
	}

	@Override
	public UserDTO getUser(String email) {
		User user = userRepository.findByEmail(email).get();
		ObjectMapper om = new ObjectMapper();
		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		UserDTO userDTO = om.convertValue(user, UserDTO.class);
		return userDTO;
	}

	@Override
	public List<UserDTO> getList() {
		List<User> list = userRepository.findAll();
		ObjectMapper om = new ObjectMapper();
		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
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
		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		User user = om.convertValue(userDTO, User.class);
		user = userRepository.save(user);
		userDTO.setId(user.getId());

		return userDTO;

	}

	@Override
	public String authenticate(LoginDTO loginData) {
		User user = userRepository.findByEmail(loginData.getUserName()).orElseThrow(()->new UsernameNotFoundException(""));
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(user.getName())
				.append(user.getPassword())
				.append(SECRET_HASH);
		byte[] genPass = DigestUtils.md5Digest(stringBuilder.toString().getBytes());
		byte[] logPass = loginData.getPassword().getBytes();
		if(genPass == logPass){
			jwtTokenUtil.generateToken(CustomUserDetails.builder().user(user).build());
		}
		String token = jwtTokenUtil.generateToken(loadUserByUsername(loginData.getUserName()));
		return token;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(""));
		UserDetails userDetails = new UserDetails() {
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return null;
			}

			@Override
			public String getPassword() {
				return user.getPassword();
			}

			@Override
			public String getUsername() {
				return user.getEmail();
			}

			@Override
			public boolean isAccountNonExpired() {
				return false;
			}

			@Override
			public boolean isAccountNonLocked() {
				return false;
			}

			@Override
			public boolean isCredentialsNonExpired() {
				return false;
			}

			@Override
			public boolean isEnabled() {
				return false;
			}
		};
		return userDetails;
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<User> user = userRepository.findByEmail(username);
//		if(! user.isPresent()){
//			throw new UsernameNotFoundException("User name not found");
//		}
//		return CustomUserDetails.builder().user(user.get()).build();
//	}

}
