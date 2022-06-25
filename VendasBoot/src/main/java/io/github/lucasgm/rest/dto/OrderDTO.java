package io.github.lucasgm.rest.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {

    private Integer client;
    private BigDecimal total;
    private List<OrderItemDTO> items;

}
