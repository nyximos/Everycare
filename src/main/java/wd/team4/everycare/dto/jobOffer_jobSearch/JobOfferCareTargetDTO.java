package wd.team4.everycare.dto.jobOffer_jobSearch;

import lombok.*;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.Gender;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class JobOfferCareTargetDTO {
    private Long id;
    private String name;
    private Gender gender;
    private Long height;
    private Long weight;
    private String address;
    private String detailAddress;

    @Builder JobOfferCareTargetDTO(Long id, String name, Gender gender, Long height, Long weight, String address, String detailAddress){
        this.id=id;
        this.name=name;
        this.gender=gender;
        this.height=height;
        this.weight=weight;
        this.address=address;
        this.detailAddress=detailAddress;
    }

    public CareTarget toCareTarget(){
        return CareTarget.builder()
                .id(this.id)
                .name(this.name)
                .gender(this.gender)
                .height(this.height)
                .weight(this.weight)
                .address(this.address)
                .detailedAddress(this.detailAddress)
                .build();
    }
}
