package dev.acs.auth.module.user.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.acs.auth.module.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "User", description = "User people")
public class UserController {

  @ApiOperation(value = "Greets the world or Niteroi")
  @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public User hello(@RequestParam(required = false) boolean niteroi) {
    return User.builder().name("test").build();
  }

  @ApiOperation(value = "Greets a person given her name")
  @GetMapping(value = "/hello/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public User get(@PathVariable String name) {
    return User.builder().name(name).build();
  }
}