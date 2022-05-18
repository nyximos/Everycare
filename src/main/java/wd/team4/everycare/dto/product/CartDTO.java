package wd.team4.everycare.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    @JsonProperty
    private Long id;
    @JsonProperty
    private int quantity;
    @JsonProperty
    private int amount;
}
