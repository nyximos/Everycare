package wd.team4.everycare.dto.member;

import lombok.*;
import wd.team4.everycare.domain.MemberRole;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberInfoDTO {

    private String id;
    private String name;
    private MemberRole role;

}
