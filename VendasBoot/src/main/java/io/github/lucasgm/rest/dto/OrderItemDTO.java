package io.github.lucasgm.rest.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItemDTO {

    private Integer product;
    private Integer quantity;

}
