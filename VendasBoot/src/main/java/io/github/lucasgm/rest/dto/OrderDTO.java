package io.github.lucasgm.rest.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {

    @NotNull(message = "Informe o código do cliente")
    private Integer client;
    @NotNull(message = "Informe o total do pedido")
    private BigDecimal total;
    private List<OrderItemDTO> items;

}
