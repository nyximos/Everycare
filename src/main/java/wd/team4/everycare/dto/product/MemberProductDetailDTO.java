package wd.team4.everycare.dto.product;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.dto.ImageDTO;
import wd.team4.everycare.dto.store.StoreNameDTO;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberProductDetailDTO {

    private Long id;
    private String name;
    private int price;
    private int inventoryQuantity;
    private int isSale;
    private String comment;
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;
    private String uploadFileName;
    private String storeFileName;
    private StoreNameDTO store;
    private ProductCategoryDTO productCategory;
    private List<ImageDTO> productImagesDTOs;
}
