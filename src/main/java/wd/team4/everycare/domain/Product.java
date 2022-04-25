package wd.team4.everycare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
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

    @Column(name = "product_upload_file_name", nullable = false)
    private String uploadFileName;

    @Column(name = "product_store_file_name", nullable = false)
    private String storeFileName;

    @Column(name = "product_comment", length = 1000, nullable = false)
    private String comment;

    @Column(name = "product_is_sale", nullable = false)
    private int isSale;

    @Column(name = "product_created_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> productImages = new ArrayList<>();
}
