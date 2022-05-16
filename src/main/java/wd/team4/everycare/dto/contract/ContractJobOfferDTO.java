package wd.team4.everycare.dto.contract;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractJobOfferDTO {

    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String day;
    private String desiredStartTime;
    private String desiredEndTime;
    private int pay;
    private int amount;

}
