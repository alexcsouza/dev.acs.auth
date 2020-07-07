package dev.acs.auth.module.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO{
    private Long id;
    private String name;
    private String password;
    private String email;
    private String alias;
}
