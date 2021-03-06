package dev.acs.auth.module.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.security.sasl.AuthenticationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import dev.acs.auth.core.bean.ModelMapperBean;
import dev.acs.auth.module.login.LoginDTO;
import dev.acs.auth.module.login.TokenAuthenticationService;
import dev.acs.auth.module.user.persistence.IUserRepository;
import dev.acs.auth.module.user.persistence.User;
import dev.acs.auth.module.user.service.dto.UserDTO;

@RunWith(SpringRunner.class)
// TODO: load messages in messages.properties
//@TestPropertySource(locations = "/application.properties", properties = "baeldung.testpropertysource.one=other-property-value")
public class UserServiceTest {
	
	private UserService userService;
	private IUserRepository userRepository;
	private TokenAuthenticationService tokenAuthenticationService;
	private ModelMapperBean modelMapperBean;
	
	@Before
	public void init() {
		userRepository = mock(IUserRepository.class);
		tokenAuthenticationService = mock(TokenAuthenticationService.class);
		modelMapperBean = new ModelMapperBean();//mock(ModelMapperBean.class);
		modelMapperBean.init();
		userService = new UserService(userRepository, tokenAuthenticationService, modelMapperBean);
	}
	
	@Test
	public void whenGetUser_usingId_thenUserIsReturned() {
		Long id = 1L;
		
		when(userRepository.findById(id))
		.thenReturn(
				Optional
					.of(
							User.builder()
							.name("User 1")
							.creationDate(new Date())
							.email("admin@test.it")
							.id(id)
							.lastUpdateDate(new Date())
							.password("pass")
							.build()
						)
					);
		
		
		
		UserDTO userDTO = userService.getUser(id);
		
		assertThat(userDTO)
		.withFailMessage("User not returned on get method")
		.isNotNull();
		
		assertThat(userDTO.getId())
		.withFailMessage("User data not returned on get method")
		.isEqualTo(id);
		
		assertThat(userDTO.getEmail())
		.withFailMessage("User data not returned on get method")
		.isNotNull();
			
	}
	
	@Test
	public void whenGetUser_usingId_thenUserNotFound() {
		Long id = 1L;
		when(userRepository.findById(id)).thenReturn(Optional.ofNullable(null));
		
		// TODO: Load string from messages.properties
		assertThatThrownBy(() -> userService.getUser(id))
		.withFailMessage("Should throw IllegalArgumentException.")
		.isInstanceOf(EntityNotFoundException.class);
		
	}
	
	@Test
	public void whenGetUser_usingEmail_thenUserIsReturned() {
		String email = "admin@test.it";
		
		when(userRepository.findByEmail(email))
		.thenReturn(
				Optional
					.of(
							User.builder()
							.name("User 1")
							.creationDate(new Date())
							.email(email)
							.id(1L)
							.lastUpdateDate(new Date())
							.password("pass")
							.build()
						)
					);
		
		
		UserDTO userDTO = userService.getUser(email);
		
		assertThat(userDTO)
		.withFailMessage("User not returned on get method")
		.isNotNull();
		
		assertThat(userDTO.getEmail())
		.withFailMessage("User data not returned on get method")
		.isNotNull()
		.isEqualTo(email)
		;
		
	}
	
	@Test
	public void whenGetUser_usingEmail_thenUserNotFound() {
	
		String email = "admin@test.it";
		when(userRepository.findByEmail(email)).thenReturn(Optional.ofNullable(null));
		assertThatThrownBy(() -> userService.getUser(email))
		.withFailMessage("Should throw IllegalArgumentException.")
		.isInstanceOf(EntityNotFoundException.class);

	}
	
	@Test
	public void whenRegisterUser_thenUserIsResturned() {
		
		UserDTO userDTO = UserDTO
				.builder()
				.name("user name")
				.email("admin@test.it")
				.alias("user alias")
				.password("123@Admin")
				.build();
		
		User userPersisted = User
				.builder()
				.id(2L)
				.name("user name")
				.email("admin@test.it")
				.password("123@Admin")
				.build();
		
		when(userRepository.save(Mockito.any())).thenReturn(userPersisted);
		
		 
		userDTO = userService.registerUser(userDTO);
		
		assertThat(userDTO)
		.withFailMessage("User returned can't be null.")
		.isNotNull();
		
		assertThat(userDTO.getId())
		.withFailMessage("User id should be returned.")
		.isNotNull()
		.isEqualTo(userPersisted.getId());
		
		assertThat(userDTO.getId())
		.withFailMessage("User password should be ommited on return.")
		.isNotNull()
		.isEqualTo(userPersisted.getId());
		
	}
	
	@Test
	public void whenRegisterUser_thenUserEmailIsAlreadyRegistered() {
		UserDTO userDTO = UserDTO
				.builder()
				.name("user name")
				.email("admin@test.it")
				.alias("user alias")
				.password("123@Admin")
				.build();
		
		User userPersisted = User
				.builder()
				.id(2L)
				.name("user name")
				.email("admin@test.it")
				.password("123@Admin")
				.build();
		
		when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(userPersisted));
		
		// TODO: Load string from messages.properties
		assertThatThrownBy(() -> userService.registerUser(userDTO))
		.isInstanceOf(IllegalArgumentException.class)
		.hasMessage("User already registered");
		

	}
	
	@Test
	public void whenRegisterUser_thenUserIdIsAlreadyRegistered() {
		UserDTO userDTO = UserDTO
				.builder()
				.id(2L)
				.name("user name")
				.email("admin@test.it")
				.alias("user alias")
				.password("123@Admin")
				.build();
		
//		User userPersisted = User
//				.builder()
//				.id(2L)
//				.name("user name")
//				.email("admin@test.it")
//				.password("123@Admin")
//				.build();
		
//		when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(userPersisted));
		
		// TODO: Load string from messages.properties
		assertThatThrownBy(() -> userService.registerUser(userDTO))
		.isInstanceOf(IllegalArgumentException.class)
		.hasMessage("User already registered");
		

	}
	
	
	@Test
	public void whenRegisterUser_thenPasswordIsNotWellFormated() {
		UserDTO userDTO = UserDTO
				.builder()
				.name("user name")
				.email("admin@test.it")
				.alias("user alias")
				.password("123Admin")
				.build();
		
		when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.ofNullable(null));
		// TODO: Load string from messages.properties
		assertThatThrownBy(() -> userService.registerUser(userDTO))
		.withFailMessage("Should throw IllegalArgumentException.")
		.isInstanceOf(IllegalArgumentException.class)
		.withFailMessage("Should contains the password error massage.")
		.hasMessage("Invalid format for passord.");
	}
	
	@Test
	public void whenRegisterUser_thenEmailIsNotWellFormated() {
		UserDTO userDTO = UserDTO
				.builder()
				.name("user name")
				.email("admin@test")
				.alias("user alias")
				.password("123Admin")
				.build();
		
		when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.ofNullable(null));
		// TODO: Load string from messages.properties
		assertThatThrownBy(() -> userService.registerUser(userDTO))
			.withFailMessage("Should throw IllegalArgumentException.")
			.isInstanceOf(IllegalArgumentException.class)
			.withFailMessage("Should contains the email error massage.")
			.hasMessage("Invalid format for email.");
	}

	
	@Test
	public void whenRegisterUser_thenUserDTOIsReturnedWhithPassoword() {
		UserDTO userDTO = UserDTO
				.builder()
				.name("user name")
				.email("admin@test.it")
				.alias("user alias")
				.password("123@Admin")
				.build();
		
		User userPersisted = User
				.builder()
				.id(2L)
				.name("user name")
				.email("admin@test.it")
				.password("123@Admin")
				.build();
		
		when(userRepository.save(Mockito.any())).thenReturn(userPersisted);
		 
		userDTO = userService.registerUser(userDTO);
		
		assertThat(userDTO.getPassword())
		.withFailMessage("Password can't be returned on user registerd.")
		.isNull();
		
	}	
	
	@Test
	public void whenAuthenticate_thenUserNameIsIncorrect() {
		
		LoginDTO userDTO = LoginDTO
				.builder()
				.password("123@Admin")
				.username("admin123")
				.build();
		
		assertThatThrownBy(() -> {userService.authenticate(userDTO);})
		.withFailMessage("Should throw UsernameNotFoundException.")
		.isInstanceOf(UsernameNotFoundException.class);
		
	}

	@Test
	public void whenAuthenticate_thenPasswordIsIncorrect() {
		
		LoginDTO userDTO = LoginDTO
				.builder()
				.password("WrongPassword")
				.username("admin@test.it")
				.build();
		
		
		User userPersisted = User
				.builder()
				.id(2L)
				.name("user name")
				.email("admin@test.it")
				.password("123@Admin")
				.build();
		
		when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(userPersisted));
		try {
		userService.authenticate(userDTO);
		}catch(Throwable e) {
			System.out.println(e);
		}
		
		assertThatThrownBy(() -> {userService.authenticate(userDTO);})
		.withFailMessage("Should throw AuthenticationException.")
		.isInstanceOf(AuthenticationException.class)
		.withFailMessage("Should contains the invalid password error massage.")
		.hasMessage("Invalid username or password")
		;
		
	}
	
	
}
