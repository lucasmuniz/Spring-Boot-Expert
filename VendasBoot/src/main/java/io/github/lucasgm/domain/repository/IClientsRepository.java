package io.github.lucasgm.domain.repository;

import io.github.lucasgm.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IClientsRepository extends JpaRepository<Client, Integer> {

    //query method
    List<Client> findByNameContaining(String name);

    @Query(" select c from Client c left join fetch c.orders where c.id =:id ")
    Client findClientFetchOrders(@Param("id") Integer id);
}
