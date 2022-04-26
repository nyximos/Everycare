package wd.team4.everycare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@DynamicUpdate
@Getter
@NoArgsConstructor
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

    @OneToMany(mappedBy = "productCategory")
    private List<Product> products = new ArrayList<>();
}
