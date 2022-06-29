package io.github.lucasgm.service.impl;

import io.github.lucasgm.domain.entity.User;
import io.github.lucasgm.domain.repository.IUserRepository;
import io.github.lucasgm.exception.InvalidPasswordException;
import io.github.lucasgm.rest.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private IUserRepository userRepository;

    @Transactional
    public UserDTO save(User user) {
        userRepository.save(user);
        return new UserDTO(user.getLogin());
    }

    public UserDetails authenticate(User user) {
        UserDetails userDetails = loadUserByUsername(user.getLogin());
        boolean ok = encoder.matches(user.getPassword(), userDetails.getPassword());
        if (ok) {
            return userDetails;
        }
        throw new InvalidPasswordException();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados"));

        String[] roles = user.isAdmin() ? new String[]{"ADMIN, USER"} : new String[]{"USER"};

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

}
