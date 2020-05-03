package dev.acs.auth.module.login;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Login", tags = {"Login"})
@RequestMapping(path = {"/"})
public class LoginController {

    @ApiOperation(value = "Login")
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public void login(@RequestBody LoginDTO loginDTO){
    	throw new NotImplementedException("Not implemented because spring-boot intercepts login process");
    }
    
}

