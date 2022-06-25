package io.github.lucasgm.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderInfoDTO {

    private Integer code;
    private String cpf;
    private String clientName;
    private BigDecimal total;
    private String date;
    private List<OrderItemInfoDTO> items;

}
