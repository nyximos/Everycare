package wd.team4.everycare.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.dto.UploadFile;
import wd.team4.everycare.dto.product.MemberProductsViewDTO;
import wd.team4.everycare.dto.product.ProductFormDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(name = "product_upload_uploadFileName", nullable = false)
    private String uploadFileName;

    @Column(name = "product_store_uploadFileName", nullable = false)
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

    @Builder
    public Product(Long id, String name, int price, int inventoryQuantity, String comment, int isSale, LocalDateTime createdAt, Store store, ProductCategory productCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inventoryQuantity = inventoryQuantity;
        this.comment = comment;
        this.isSale = isSale;
        this.createdAt = createdAt;
        this.store = store;
        this.productCategory = productCategory;
    }

    public MemberProductsViewDTO toMemberProductsViewDTO() {
        return MemberProductsViewDTO.builder()
                .id(this.id)
                .name(this.name)
                .price(this.price)
                .inventoryQuantity(this.inventoryQuantity)
                .isSale(this.isSale)
                .uploadFileName(this.uploadFileName)
                .storeFileName(this.storeFileName)
                .build();
    }

    public ProductFormDTO toProductFormDTO() {
        return ProductFormDTO.ProductFormDTOBuilder()
                .name(this.name)
                .price(this.price)
                .inventoryQuantity(this.inventoryQuantity)
                .comment(this.comment)
                .isSale(this.isSale)
                .build();
    }

    public void saveTime(LocalDateTime time) {
        this.createdAt = time;
    }

    public void saveImage(UploadFile attachFile){
        this.uploadFileName = attachFile.getUploadFileName();
        this.storeFileName = attachFile.getStoreFileName();
    }

    public void saveStore(Store store){
        this.store = store;
    }

    public void saveProductCategory(ProductCategory productCategory){
        this.productCategory = productCategory;
    }
}
