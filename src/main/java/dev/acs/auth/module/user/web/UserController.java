package dev.acs.auth.module.user.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.acs.auth.module.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "User", description = "User people")
@RequestMapping(path = {"/v1/user"})
public class UserController {

  @ApiOperation(value = "Get user information")
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public User get(@PathVariable(required = true) long id) {
    return User.builder().id(id).name("test").build();
  }

  @ApiOperation(value = "Get all users information")
  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<User> getList() {
    return Arrays.asList(
      new User[]{
        User.builder().id(1L).name("Alex").build(),
        User.builder().id(2L).name("Mio").build(),
        User.builder().id(3L).name("Manchinha").build()
      });
  }
}