package io.github.lucasgm.domain.repository;

import io.github.lucasgm.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Integer> {
}
