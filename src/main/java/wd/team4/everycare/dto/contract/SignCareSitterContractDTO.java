package wd.team4.everycare.dto.contract;

import lombok.*;
import wd.team4.everycare.domain.CareSitter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignCareSitterContractDTO {

    private Long id;
    private String name;
    private String preferredType;
    private int cctvAgreement;
    private int vaccination;
    private String introduction;
    private int disclosureStatus;

    public CareSitter toCareSitter(){
        return CareSitter.builder()
                .id(this.id)
                .name(this.name)
                .preferredType(this.preferredType)
                .cctvAgreement(this.cctvAgreement)
                .vaccination(this.vaccination)
                .introduction(this.introduction)
                .disclosureStatus(this.disclosureStatus)
                .build();
    }
}
