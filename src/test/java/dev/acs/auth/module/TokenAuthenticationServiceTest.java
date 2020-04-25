package dev.acs.auth.module;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import dev.acs.auth.module.login.TokenAuthenticationService;
import dev.acs.auth.module.user.persistence.User;
import dev.acs.auth.module.user.security.CustomUserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RunWith(SpringRunner.class)
public class TokenAuthenticationServiceTest {
	
	@TestConfiguration
    static class TokenAuthenticationServiceTestContextConfiguration {
        @Bean
        public TokenAuthenticationService tokenAuthService() {
            return new TokenAuthenticationService();
        }
    }
	
	// Default values for TokenAuthenticationService attributes
    private String expirationTime = "1000000000";
    private String secret = "123-e#1";
    private String prefix = "acs";
    private String headerString = "Authentication";
	
	@Autowired
	private TokenAuthenticationService tokenAuthService;

	@Before
	public void initAtrributes() {
		ReflectionTestUtils.setField(tokenAuthService, "expirationTime", expirationTime, String.class);
    	ReflectionTestUtils.setField(tokenAuthService, "secret", secret, String.class);
    	ReflectionTestUtils.setField(tokenAuthService, "prefix", prefix, String.class);
    	ReflectionTestUtils.setField(tokenAuthService, "headerString", headerString, String.class);
	}
	
	@Test
	public void whenAddAuthentication_usingCustomUserDetaisl_thenTokenIsReturned() {
		
		String userExpected = "admin@test";
		CustomUserDetails cud = CustomUserDetails.builder().user(User.builder().email(userExpected).build()).build();

		String token = tokenAuthService.addAuthentication(cud);
		String user = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
		
		assertThat(token)
			.as("Token")
			.withFailMessage("Generated token is empty")
			.isNotEmpty();
		
		assertThat(user)
			.as("User")
			.withFailMessage("User data extracted from token not match:\n%s\n%s", user, userExpected)
			.isEqualTo(userExpected);
		
	}
	
	@Test
	public void whenAddAuthentication_usingHttpServletResponse_thenTokenIsSettedInHeader() {
		
		String userExpected = "admin@test";
		CustomUserDetails cud = CustomUserDetails.builder().user(User.builder().email(userExpected).build()).build();
        HttpServletResponse response = mock(HttpServletResponse.class);  
		response = tokenAuthService.addAuthentication(response, cud.getUsername());
		
		// Testins just that is called for now
		verify(response, times(1)).addHeader(Mockito.eq(headerString), Mockito.anyString());
		
	}

	@Test
    public void whenGetAuthentication_thenAuthenticationIsReturned() {
    	
		String userExpected = "admin@test";
    	HttpServletRequest request = mock(HttpServletRequest.class);
    	when(request.getHeader(headerString)).thenReturn(
    			Jwts.builder()
	                .setSubject(userExpected)
	                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expirationTime)))
	                .signWith(SignatureAlgorithm.HS512, secret)
	                .compact()
	            );
    	
    	Authentication auth = tokenAuthService.getAuthentication(request);
    	
    	// Assert that username is setted on token only
    	assertThat(auth.getName())
    		.withFailMessage("Auth name not present in token on request header: %s", userExpected)
    		.isEqualTo(userExpected);

    	
    }
	

	@Test
    public void whenGetAuthentication_thenNullIsReturned() {
    	
    	HttpServletRequest request = mock(HttpServletRequest.class);
    	when(request.getHeader(headerString)).thenReturn(null);
    	Authentication auth = tokenAuthService.getAuthentication(request);
    	
    	// Assert that auth is null if there's no auth information on request headers
    	assertThat(auth)
    		.withFailMessage("Auth should not be returned becouse there's not auth information on request headers")
    		.isNull();
    	
    }
	
}
