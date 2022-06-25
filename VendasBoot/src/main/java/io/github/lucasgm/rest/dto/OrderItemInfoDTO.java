package io.github.lucasgm.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderItemInfoDTO {

    private String productDescription;
    private BigDecimal price;
    private Integer quantity;
}
