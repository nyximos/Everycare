package wd.team4.everycare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "product_seq_generator",
            sequenceName = "product_seq",
            initialValue = 1, allocationSize = 1)
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq_generator")
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Column(name = "product_name", length = 255, nullable = false)
    private String name;

    @Column(name = "product_price", nullable = false)
    private int price;

    @Column(name = "product_inventory_quantity", nullable = false)
    private int inventoryQuantity;

    @Column(name = "product_file_name", length = 255, nullable = false)
    private String fileName;

    @Column(name = "product_path", length = 255, nullable = false)
    private String filePath;

    @Column(name = "product_comment", length = 1000, nullable = false)
    private String comment;

    @Column(name = "product_is_sale", nullable = false)
    private int isSale;

    @Column(name = "product_created_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
