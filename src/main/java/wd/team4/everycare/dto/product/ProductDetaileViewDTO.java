package wd.team4.everycare.dto.product;

import lombok.*;
import wd.team4.everycare.dto.store.StoreNameDTO;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDetaileViewDTO {

    private Long id;
    private String name;
    private int price;
    private String storeFileName;
    private String comment;
    private int isSale;
    private LocalDateTime createdAt;
    private StoreNameDTO store;
    private ProductCategoryDTO productCategory;

}
