package wd.team4.everycare.dto.contract;

import lombok.*;
import wd.team4.everycare.dto.caretarget.CareTargetDetailDTO;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareSitterContractDTO {

    private ContractJobOfferDTO contractJobOfferDTO;
    private CareTargetDetailDTO careTargetDetailDTO;

}
