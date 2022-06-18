package wd.team4.everycare.dto.jobOffer_jobSearch;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import wd.team4.everycare.dto.careSitter.CareSitterDTO;

@Getter
@Setter
@Builder
public class AnnounceJobOfferDTO {

    private JobOfferDTO jobOffer;
    private CareSitterDTO careSitter;

}
