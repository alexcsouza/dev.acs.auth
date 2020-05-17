package dev.acs.auth.module.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.acs.auth.module.user.service.IUserService;
import dev.acs.auth.module.user.service.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "User", tags = "User")
@RequestMapping(path = {"/api/v1/user"})
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "Get user information")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class)
    public UserDTO get(@PathVariable(required = true) long id) {
        return userService.get(id);
    }

    @ApiOperation(value = "Get all users information")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class)
    public Page<UserDTO> getList(
    		Integer size,
    		Integer page
    	) {
        return userService.getList(PageRequest.of(page, size));
    }

    @ApiOperation(value = "Register new user")
    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }

}