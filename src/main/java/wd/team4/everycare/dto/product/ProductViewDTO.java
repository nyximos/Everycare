package wd.team4.everycare.dto.product;

import lombok.*;
import wd.team4.everycare.dto.store.StoreNameDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductViewDTO {

    private Long id;
    private String name;
    private String uploadFileName;
    private String storeFileName;
    private StoreNameDTO store;
}
