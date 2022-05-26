package wd.team4.everycare.dto.order;

import lombok.*;
import wd.team4.everycare.dto.member.MemberNameDTO;
import wd.team4.everycare.dto.product.ProductViewDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductDTO {

    private int quantity;
    private int amount;
    private OrderDTO order;
    private ProductViewDTO product;
    private MemberNameDTO member;
}
