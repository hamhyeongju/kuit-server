package kuit.server.service.authService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@AllArgsConstructor
public class AuthUser {

    private Long id;
    private String username;
    private String password;

}
