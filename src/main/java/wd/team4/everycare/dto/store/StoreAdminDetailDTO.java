package wd.team4.everycare.dto.store;

import lombok.*;
import wd.team4.everycare.dto.member.MemberNameDTO;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StoreAdminDetailDTO {
    private Long id;
    private String name;
    private String url;
    private int businessLicenseNumber;
    private String email;
    private String operationStartTime;
    private String operationEndTime;
    private String lunchStartTime;
    private String lunchEndTime;
    private String closedDay;
    private String companyCorporationName;
    private String representativeName;
    private String businessLocation;
    private String customerServiceNumber;
    private LocalDateTime createdAt;
    private MemberNameDTO member;
}
