package io.github.lucasgm.domain.repository;

import io.github.lucasgm.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByLogin(String login);

}
