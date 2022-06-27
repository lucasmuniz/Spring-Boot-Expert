package io.github.lucasgm.rest.controller;

import io.github.lucasgm.domain.entity.User;
import io.github.lucasgm.rest.dto.UserDTO;
import io.github.lucasgm.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO save(@Valid @RequestBody User user) {
        String passCripto = passwordEncoder.encode(user.getPassword());
        user.setPassword(passCripto);
        return userService.save(user);
    }

}
