package io.github.lucasgm.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ITEM_PEDIDO")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "PEDIDO_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "PRODUTO_ID")
    private Product product;

    @Column(name = "QUANTIDADE")
    private Integer quantity;

}
