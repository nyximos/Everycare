package wd.team4.everycare.dto.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
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
    private String uploadFileName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    private StoreNameDTO store;
    private ProductCategoryDTO productCategory;
}
