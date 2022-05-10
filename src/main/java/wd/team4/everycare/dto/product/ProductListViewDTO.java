package wd.team4.everycare.dto.product;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.dto.store.StoreNameDTO;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductListViewDTO {
    private Long id;
    private String name;
    private int price;
    private String storeFileName;
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;
    private StoreNameDTO store;
    private ProductCategoryDTO productCategory;
}
