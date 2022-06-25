package io.github.lucasgm.service;

import io.github.lucasgm.domain.entity.Order;
import io.github.lucasgm.domain.enums.OrderStatusEnum;
import io.github.lucasgm.rest.dto.OrderDTO;

import java.util.Optional;

public interface IOrderService {

    Order save(OrderDTO orderDTO);

    Optional<Order> getOrder(Integer id);

    void statusUpdate(Integer id, OrderStatusEnum status);

}
