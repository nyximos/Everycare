package wd.team4.everycare.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import wd.team4.everycare.dto.UploadFile;
import wd.team4.everycare.dto.product.MemberProductListViewDTO;
import wd.team4.everycare.dto.product.ProductFormDTO;
import wd.team4.everycare.service.exception.NotEnoughStockException;
import wd.team4.everycare.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;


    public void addStock(int quantity) {
        this.inventoryQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.inventoryQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.inventoryQuantity = restStock;
    }

    public MemberProductListViewDTO toMemberProductsViewDTO() {
        return MemberProductListViewDTO.builder()
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

    public void updateProduct(ProductFormDTO productFormDTO) {
        if(StringUtils.isNotBlank(productFormDTO.getName())) {
            this.name = productFormDTO.getName();
        }
        if(StringUtils.isNotBlank(String.valueOf(productFormDTO.getPrice()))) {
            this.price = productFormDTO.getPrice();
        }
        if(StringUtils.isNotBlank(String.valueOf(productFormDTO.getInventoryQuantity()))) {
            this.price = productFormDTO.getInventoryQuantity();
        }
        if(StringUtils.isNotBlank(productFormDTO.getComment())) {
            this.comment = productFormDTO.getComment();
        }
        if(StringUtils.isNotBlank(String.valueOf(productFormDTO.getIsSale()))) {
            this.isSale = productFormDTO.getIsSale();
        }
    }


}
