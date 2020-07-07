package dev.acs.auth.module.login;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.security.sasl.AuthenticationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import dev.acs.auth.core.bean.ModelMapperBean;
import dev.acs.auth.module.login.dto.LoginDTO;
import dev.acs.auth.module.login.service.LoginService;
import dev.acs.auth.module.login.service.TokenAuthenticationService;
import dev.acs.auth.module.user.persistence.IUserRepository;
import dev.acs.auth.module.user.persistence.User;

@RunWith(SpringRunner.class)
public class LoginServiceTest {
	
	private LoginService loginService;
	private IUserRepository userRepository;
	private TokenAuthenticationService tokenAuthenticationService;
	private ModelMapperBean modelMapperBean;
	
	@Before
	public void init() {
		userRepository = mock(IUserRepository.class);
		tokenAuthenticationService = mock(TokenAuthenticationService.class);
		modelMapperBean = new ModelMapperBean();//mock(ModelMapperBean.class);
		modelMapperBean.init();
		boolean confirmationRequiredToAuthenticate = false;
		loginService = new LoginService(tokenAuthenticationService, userRepository, confirmationRequiredToAuthenticate);
	}
	
	
	@Test
	public void whenAuthenticate_thenUserNameIsIncorrect() {
		
		LoginDTO userDTO = LoginDTO
				.builder()
				.password("123@Admin")
				.username("admin123")
				.build();
		
		assertThatThrownBy(() -> {loginService.authenticate(userDTO);})
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
				
		assertThatThrownBy(() -> {loginService.authenticate(userDTO);})
		.withFailMessage("Should throw AuthenticationException.")
		.isInstanceOf(AuthenticationException.class)
		.withFailMessage("Should contains the invalid password error massage.")
		.hasMessage("Invalid username or password")
		;
		
	}
}
