package io.github.lucasgm.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "PRODUTO")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DESCRICAO")
    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String description;

    @Column(name = "PRECO_UNITARIO")
    @NotNull(message = "{campo.preco.obrigatorio}")
    private BigDecimal price;

}

