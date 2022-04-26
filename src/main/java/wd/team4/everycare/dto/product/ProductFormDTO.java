package wd.team4.everycare.dto.product;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import wd.team4.everycare.domain.Product;
import wd.team4.everycare.domain.Store;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductFormDTO {

    private String name;
    private int price;
    private int inventoryQuantity;
    private String comment;
    private int isSale;
    private Store store;
    private Long productCategory;
    private MultipartFile attachFile;
    private List<MultipartFile> attachFiles;

    @Builder(builderMethodName = "ProductFormDTOBuilder")
    public ProductFormDTO(String name, int price, int inventoryQuantity, String comment, int isSale) {
        this.name = name;
        this.price = price;
        this.inventoryQuantity = inventoryQuantity;
        this.comment = comment;
        this.isSale = isSale;
    }

    @Builder(builderMethodName = "ProductImageDTOBuilder")
    public ProductFormDTO(List<MultipartFile> attachFiles) {
        this.attachFiles = attachFiles;
    }

    public Product toProduct() {
        return Product.builder()
                .name(this.name)
                .price(this.price)
                .inventoryQuantity(this.inventoryQuantity)
                .comment(this.comment)
                .isSale(this.isSale)
                .store(this.store)
                .build();
    }
}
