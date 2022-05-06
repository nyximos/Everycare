package wd.team4.everycare.dto.product;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberProductsViewDTO {

    private Long id;
    private String name;
    private int price;
    private int inventoryQuantity;
    private int isSale;
    private String uploadFileName;
    private String storeFileName;

}
