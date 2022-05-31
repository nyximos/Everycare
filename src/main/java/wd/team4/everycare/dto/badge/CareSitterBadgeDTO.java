package wd.team4.everycare.dto.badge;

import lombok.*;
import wd.team4.everycare.dto.careSitter.CareSitterNameDTO;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareSitterBadgeDTO {

    private Long id;
    private CareSitterNameDTO careSitter;
    private BadgeNameDTO badge;
}
