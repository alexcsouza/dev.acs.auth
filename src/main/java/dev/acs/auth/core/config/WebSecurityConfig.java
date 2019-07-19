package dev.acs.auth.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.acs.auth.core.security.AuthenticationFilter;
import dev.acs.auth.module.user.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security",
                "/swagger-ui.html", "/webjars/**");
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {



        auth
        .userDetailsService(userService);

        /*
        .inMemoryAuthentication()
        .withUser("admin")
        .password(
            encoder()
            .encode("adminPass")
        )
        .roles("ADMIN")
        .and()
        .withUser("user")
        .password(
            encoder().encode("userPass")
        )
        .roles("USER");
        */
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
        .csrf()
        .disable()
        /*
        .authorizeRequests()
        .anyRequest()
        .permitAll()
        */
        ;
    }
   
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager(),
        userService
        );
        return filter;
    }

}