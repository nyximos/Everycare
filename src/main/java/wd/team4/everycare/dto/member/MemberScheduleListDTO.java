package wd.team4.everycare.dto.member;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberScheduleListDTO {

    private Long id;
    private String name;
    private LocalDateTime startDatetime;
    private LocalDateTime endDatetime;

}