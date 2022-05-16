package wd.team4.everycare.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product_image")
@SequenceGenerator(name = "product_image_seq_generator",
        sequenceName = "product_image_seq",
        initialValue = 1, allocationSize = 1)
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_image_seq_generator")
    @Column(name = "product_image_id", nullable = false)
    private Long id;

    @Column(name = "product_image_upload_file_name")
    private String uploadFileName;

    @Column(name = "product_image_store_file_name")
    private String storeFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

}
