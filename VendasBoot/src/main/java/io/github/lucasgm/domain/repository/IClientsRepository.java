package io.github.lucasgm.domain.repository;

import io.github.lucasgm.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IClientsRepository extends JpaRepository<Client, Integer> {

    //query method
    List<Client> findByNameContaining(String name);
}
