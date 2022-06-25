package io.github.lucasgm.rest.controller;

import io.github.lucasgm.rest.dto.OrderDTO;
import io.github.lucasgm.service.IOrderService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

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

}
