package dev.acs.auth.module.login;

import dev.acs.auth.module.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Login")
@RequestMapping(path = {"/"})
public class LoginController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "Get user information")
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> get(@RequestBody LoginDTO loginDTO) {
        String token = userService.authenticate(loginDTO);
        return ResponseEntity.ok(token);
    }
}

