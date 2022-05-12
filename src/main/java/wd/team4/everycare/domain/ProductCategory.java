package wd.team4.everycare.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import wd.team4.everycare.dto.product.ProductCategoryDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@DynamicUpdate
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name = "product_category_seq_generator",
        sequenceName = "product_category_seq",
        initialValue = 1, allocationSize = 1)
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_seq_generator")
    @Column(name = "product_category_id", nullable = false)
    private Long id;

    @Column(name = "product_category_name", length = 50, nullable = false)
    private String name;

    @Column(name = "product_category_level", nullable = false)
    private int level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_parent")
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "productCategory")
    private List<Product> products = new ArrayList<>();

    public ProductCategoryDTO toDTO() {
        return ProductCategoryDTO.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
