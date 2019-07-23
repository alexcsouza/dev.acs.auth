package dev.acs.auth.module.login;

import dev.acs.auth.module.user.service.IUserService;
import dev.acs.auth.module.user.service.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Login", description = "Login ")
@RequestMapping(path = {"/"})
public class LoginController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "Get user information")
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserDTO get(@RequestBody LoginDTO id) {
        return userService.getUser(id.getUserName());
    }
}

