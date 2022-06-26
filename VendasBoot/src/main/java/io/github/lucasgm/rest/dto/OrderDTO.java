package io.github.lucasgm.rest.dto;

import io.github.lucasgm.validation.NotEmptyList;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {

    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    private Integer client;
    @NotNull(message = "{campo.total-pedido.obrigatorio}")
    private BigDecimal total;
    @NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
    private List<OrderItemDTO> items;

}
