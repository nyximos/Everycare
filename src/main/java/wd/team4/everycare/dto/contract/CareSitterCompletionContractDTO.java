package wd.team4.everycare.dto.contract;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareSitterCompletionContractDTO {

    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private int amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime payDateTime;

    private Long jobOfferId;
    private String day;
    private String memberId;
    private String memberName;

    private Long careTargetId;
    private String careTargetName;
    private String careTargetImage;
    private String gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;
    private String zipcode;
    private String address;
    private String detailedAddress;
    private int longTermCareGrade;
    private int pet;
    private int isCctvAgreement;
    private String careType;
    private int coronaTest;

}
