package io.github.lucasgm.service.impl;

import io.github.lucasgm.domain.entity.Client;
import io.github.lucasgm.domain.entity.Order;
import io.github.lucasgm.domain.entity.OrderItem;
import io.github.lucasgm.domain.entity.Product;
import io.github.lucasgm.domain.repository.IClientsRepository;
import io.github.lucasgm.domain.repository.IOrderItemRepository;
import io.github.lucasgm.domain.repository.IOrderRepository;
import io.github.lucasgm.domain.repository.IProductsRepository;
import io.github.lucasgm.exception.BusinessException;
import io.github.lucasgm.rest.dto.OrderDTO;
import io.github.lucasgm.rest.dto.OrderItemDTO;
import io.github.lucasgm.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IClientsRepository clientsRepository;
    private final IProductsRepository productsRepository;
    private final IOrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public Order save(OrderDTO orderDTO) {
        Client client = clientsRepository.
                findById(orderDTO.getClient())
                .orElseThrow(() -> new BusinessException("Código de cliente inválido: " + orderDTO.getClient()));

        Order order = new Order();
        order.setTotal(orderDTO.getTotal());
        order.setOrderDate(LocalDate.now());
        order.setClient(client);

        List<OrderItem> orderItems = convertItems(order, orderDTO.getItems());
        orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);
        order.setItems(orderItems);

        return order;
    }

    private List<OrderItem> convertItems(Order order, List<OrderItemDTO> items) {
        if (items.isEmpty()) {
            throw new BusinessException("Não é possível realizar um pedido sem items");
        }
        return items
                .stream()
                .map(dto -> {
                    Product product = productsRepository
                            .findById(dto.getProduct())
                            .orElseThrow(() -> new BusinessException("Código de Produto inválido: " + dto.getProduct()));


                    OrderItem orderItem = new OrderItem();
                    orderItem.setQuantity(dto.getQuantity());
                    orderItem.setOrder(order);
                    orderItem.setProduct(product);
                    return orderItem;
                }).collect(Collectors.toList());
    }
}
