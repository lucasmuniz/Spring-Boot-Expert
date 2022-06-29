package io.github.lucasgm.rest.controller;

import antlr.Token;
import io.github.lucasgm.domain.entity.User;
import io.github.lucasgm.exception.InvalidPasswordException;
import io.github.lucasgm.rest.dto.CredentialsDTO;
import io.github.lucasgm.rest.dto.TokenDTO;
import io.github.lucasgm.rest.dto.UserDTO;
import io.github.lucasgm.security.jwt.JwtService;
import io.github.lucasgm.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO save(@Valid @RequestBody User user) {
        String passCripto = passwordEncoder.encode(user.getPassword());
        user.setPassword(passCripto);
        return userService.save(user);
    }

    @PostMapping("/auth")
    public TokenDTO authenticate(@RequestBody CredentialsDTO credentialsDTO) {
        try {
            User user = User.builder()
                    .login(credentialsDTO.getLogin())
                    .password(credentialsDTO.getPassword()).build();
            UserDetails authenticatedUserDetail = userService.authenticate(user);
            String token = jwtService.tokenGen(user);
            return new TokenDTO(user.getLogin(), token);
        } catch (UsernameNotFoundException | InvalidPasswordException exception) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, exception.getMessage());
        }
    }

}
