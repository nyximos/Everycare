package wd.team4.everycare.dto.product;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductImageDTO {

    private Long id;
    private String storeFileName;

}
