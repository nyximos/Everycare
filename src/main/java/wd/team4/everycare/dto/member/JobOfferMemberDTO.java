package wd.team4.everycare.dto.member;

import lombok.*;
import wd.team4.everycare.domain.Gender;
import wd.team4.everycare.domain.Member;

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

    public Member toMember(){
        return Member.builder()
                .name(this.name)
                .gender(Gender.valueOf(this.gender))
                .birth(this.birth)
                .address(this.address)
                .email(this.email)
                .phone(this.phone)
                .build();
    }
}
