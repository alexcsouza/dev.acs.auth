package dev.acs.auth.module.login.service;

import javax.security.sasl.AuthenticationException;

import dev.acs.auth.module.login.dto.LoginDTO;

public interface ILoginService {
	String authenticate(LoginDTO userName) throws AuthenticationException;
}
