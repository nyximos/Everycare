package wd.team4.everycare.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import wd.team4.everycare.dto.order.OrderProductDTO;

import javax.persistence.*;

@Getter
@Entity
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(name = "order_product_is_review")
    private int review;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public void cancel() {
        getProduct().addStock(quantity);
    }

    public OrderProductDTO toOrderProductDTO(){
        return OrderProductDTO.builder()
                .quantity(this.quantity)
                .amount(this.amount)
                .order(this.order.toOrderDTO())
                .product(this.product.toProductViewDTO())
                .build();
    }

    public void reviewed(OrderProduct orderProduct){
        orderProduct.review = 1;
    }
}
