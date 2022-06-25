package io.github.lucasgm.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "PEDIDO")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Client client;

    @Column(name = "DATA_PEDIDO")
    private LocalDate orderDate;

    @Column(name = "TOTAL", precision = 20, scale = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> items;

}
