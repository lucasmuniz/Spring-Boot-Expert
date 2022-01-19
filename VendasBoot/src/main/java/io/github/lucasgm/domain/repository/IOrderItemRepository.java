package io.github.lucasgm.domain.repository;

import io.github.lucasgm.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
