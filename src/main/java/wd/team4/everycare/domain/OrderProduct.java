package wd.team4.everycare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "order_product_seq_generator",
        sequenceName = "order_product_seq",
        initialValue = 1, allocationSize = 1)
public class OrderProduct {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_product_seq_generator")
    @Column(name = "order_product_id")
    private Long id;

    @Column(name = "order_product_quantity", nullable = false)
    private int quantity;

    @Column(name = "order_product_amount", nullable = false)
    private int amount;

    @Column(name = "order_product_is_review", length = 5)
    private int review;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
