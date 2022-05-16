package wd.team4.everycare.dto.product;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WishListDTO {

    private Long id;
    private String name;
    private int price;
    private String storeFileName;
    private String storeName;

}
