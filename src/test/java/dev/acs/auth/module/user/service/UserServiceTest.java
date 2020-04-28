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
import org.springframework.test.context.junit4.SpringRunner;

import dev.acs.auth.module.login.TokenAuthenticationService;
import dev.acs.auth.module.user.persistence.IUserRepository;
import dev.acs.auth.module.user.persistence.User;
import dev.acs.auth.module.user.service.dto.UserDTO;

@RunWith(SpringRunner.class)
public class UserServiceTest {
	
	private UserService userService;
	private IUserRepository userRepository;
	private TokenAuthenticationService tokenAuthenticationService;

	@Before
	public void init() {
		userRepository = mock(IUserRepository.class);
		tokenAuthenticationService = mock(TokenAuthenticationService.class);
		userService = new UserService(userRepository, tokenAuthenticationService);
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
		
	}
	
	@Test
	public void whenRegisterUser_thenUserIsAlreadyRegistered() {
		
	}
	
	
	@Test
	public void whenRegisterUser_thenPasswordIsNotWellFormated() {
		
	}
	
	@Test
	public void whenRegisterUser_thenEmailIsNotWellFormated() {
		
	}

	
}
