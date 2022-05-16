package wd.team4.everycare.dto.member;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobOfferMemberDTO {
    private String name;
    private String gender;
    private LocalDate birth;
    private String address;
    private String email;
    private String phone;
}
