package wd.team4.everycare.dto.member;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.domain.ActivityStatus;
import wd.team4.everycare.domain.Gender;
import wd.team4.everycare.domain.MemberRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountDTO {

    private String id;
    private String name;
    private MemberRole role;
    private Gender gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;
    private String phone;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;
    private ActivityStatus activityStatus;
    private String zipcode;
    private String address;
    private String detailedAddress;
    private LocalDateTime adminRegistrationDate;
    private String bank;
    private String accountNumber;

}
