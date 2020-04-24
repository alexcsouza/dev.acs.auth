package dev.acs.auth.module.login;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenAuthenticationService {

    // expirationTime = 10 dias
//    static final long expirationTime = 860_000_000;
//    static final String secret = "Mysecret";
//    static final String prefix = "Bearer";
//    static final String headerString = "Authorization";

    @Value("${auth.jwt.token.validity}")
    private String expirationTime;

    @Value("${auth.jwt.token.secret}")
    private String secret;
    
    @Value("${auth.jwt.token.prefix}")
    private String prefix;
    
    @Value("${auth.jwt.token.header-string}")
    private String headerString;
    
    
    
    
    public String addAuthentication(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expirationTime)))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

    }

    public void addAuthentication(HttpServletResponse response, String username) {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expirationTime)))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        response.addHeader(headerString, prefix + " " + JWT);
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(headerString);

        if (token != null) {
            // faz parse do token
            String user = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token.replace(prefix, ""))
                    .getBody()
                    .getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            }
        }
        return null;
    }

}