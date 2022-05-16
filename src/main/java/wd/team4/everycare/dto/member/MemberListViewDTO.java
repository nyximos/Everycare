package wd.team4.everycare.dto.member;

import lombok.*;
import wd.team4.everycare.domain.ActivityStatus;
import wd.team4.everycare.domain.Gender;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.domain.MemberRole;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberListViewDTO {

    private String id;
    private String name;
    private MemberRole role;
    private Gender gender;
    private LocalDate birth;
    private String phone;
    private String email;
    private LocalDateTime createdAt;
    private ActivityStatus activityStatus;

    public Member toMember(){
        return Member.builder()
                .id(this.id)
                .name(this.name)
                .role(this.role)
                .gender(this.gender)
                .birth(this.birth)
                .phone(this.phone)
                .email(this.email)
                .createdAt(this.createdAt)
                .activityStatus(this.activityStatus)
                .build();
    }

}
