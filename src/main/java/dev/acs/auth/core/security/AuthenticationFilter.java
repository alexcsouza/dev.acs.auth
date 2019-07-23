package dev.acs.auth.core.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import dev.acs.auth.module.login.LoginDTO;
import dev.acs.auth.module.user.service.IUserService;
import dev.acs.auth.module.user.service.dto.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private final IUserService usersService;
    private final String TOKEN_SECRET="h4of9eh48vmg02nfu30v27yen295hfj65";
    public AuthenticationFilter(AuthenticationManager authenticationManager,
        IUserService usersService) {
        this.usersService = usersService;
        super.setAuthenticationManager(authenticationManager);
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            LoginDTO credentials = new ObjectMapper()
                    .readValue(req.getInputStream(), LoginDTO.class);
            
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUserName(),
                            credentials.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        
        // Get User Details from Database 
        String userName = ((User) auth.getPrincipal()).getUsername();  
        UserDTO userDto = usersService.getUser(userName);    
        
        // Generate GWT
        String token = Jwts.builder()
                .setSubject(userDto.getId().toString())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong("3600000")))
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET )
                .compact();
   
        res.addHeader("Token", token);
        res.addHeader("UserID", userDto.getId().toString());
    }  
}