package io.github.lucasgm.rest.controller;

import io.github.lucasgm.domain.entity.Order;
import io.github.lucasgm.domain.entity.OrderItem;
import io.github.lucasgm.domain.enums.OrderStatusEnum;
import io.github.lucasgm.rest.dto.OrderDTO;
import io.github.lucasgm.rest.dto.OrderInfoDTO;
import io.github.lucasgm.rest.dto.OrderItemInfoDTO;
import io.github.lucasgm.rest.dto.StatusOrderUpdateDTO;
import io.github.lucasgm.service.IOrderService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    private IOrderService service;

    public OrderController(IOrderService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody OrderDTO dto) {
        return service.save(dto).getId();
    }

    @GetMapping("{id}")
    public OrderInfoDTO getById(@PathVariable Integer id) {
        return service.getOrder(id)
                .map(p -> convertInfo(p))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado."));
    }

    private OrderInfoDTO convertInfo(Order order) {
        return OrderInfoDTO.builder()
                .code(order.getId())
                .date(order.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .clientName(order.getClient().getName())
                .cpf(order.getClient().getCpf())
                .total(order.getTotal())
                .status(order.getStatus().name())
                .items(converterItemInfo(order.getItems()))
                .build();
    }

    private List<OrderItemInfoDTO> converterItemInfo(List<OrderItem> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }
        return items.stream().map(
                        item -> OrderItemInfoDTO
                                .builder()
                                .productDescription(item.getProduct().getDescription())
                                .price(item.getProduct().getPrice())
                                .quantity(item.getQuantity())
                                .build())
                .collect(Collectors.toList());
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void statusUpdate(@PathVariable Integer id, @RequestBody StatusOrderUpdateDTO dto) {
        service.statusUpdate(id, OrderStatusEnum.valueOf(dto.getNewStatus()));
    }

}
