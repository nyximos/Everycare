package wd.team4.everycare.dto.jobOffer_jobSearch;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class JobOfferCareTargetDTO {
    private String name;
    private String gender;
    private Long height;
    private Long weight;

    @Builder JobOfferCareTargetDTO(String name, String gender, Long height, Long weight){
        this.name=name;
        this.gender=gender;
        this.height=height;
        this.weight=weight;
    }
}
