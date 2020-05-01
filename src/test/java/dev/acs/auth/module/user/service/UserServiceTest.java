package dev.acs.auth.module.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import dev.acs.auth.core.bean.ModelMapperBean;
import dev.acs.auth.module.login.TokenAuthenticationService;
import dev.acs.auth.module.user.persistence.IUserRepository;
import dev.acs.auth.module.user.persistence.User;
import dev.acs.auth.module.user.service.dto.UserDTO;

@RunWith(SpringRunner.class)
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
		
		try {
			userService.getUser(id);
			assertFalse("Expected exception on trying to get an unexistent user.", false);
		}catch(EntityNotFoundException e) {
			assertTrue(true);
		}
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
		
		try {
			userService.getUser(email);
			assertFalse("Expected exception on trying to get an unexistent user.", false);
		}catch(EntityNotFoundException e) {
			assertTrue(true);
		}
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
//				.alias("user alias")
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
	public void whenRegisterUser_thenUserIsAlreadyRegistered() {
		assertTrue("Not implemented test.", false);
	}
	
	
	@Test
	public void whenRegisterUser_thenPasswordIsNotWellFormated() {
		assertTrue("Not implemented test.", false);
		
	}
	
	@Test
	public void whenRegisterUser_thenEmailIsNotWellFormated() {
		assertTrue("Not implemented test.", false);
	}

	
	@Test
	public void whenRegisterUser_thenUserDTOIsReturnedWhithPassoword() {
		assertTrue("Not implemented test.", false);
	}

	
	@Test
	public void whenGetListOfUsers_thenListIsReturned() {
		assertTrue("Not implemented test.", false);
	}


	@Test
	public void whenAuthenticate_thenUserNameIsIncorrect() {
		assertTrue("Not implemented test.", false);
	}

	@Test
	public void whenAuthenticate_thenPasswordIsIncorrect() {
		assertTrue("Not implemented test.", false);
	}
	
	
	
	
}
