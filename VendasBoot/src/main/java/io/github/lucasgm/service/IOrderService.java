package io.github.lucasgm.service;

import io.github.lucasgm.domain.entity.Order;
import io.github.lucasgm.rest.dto.OrderDTO;

public interface IOrderService {

    Order save(OrderDTO orderDTO);
}
