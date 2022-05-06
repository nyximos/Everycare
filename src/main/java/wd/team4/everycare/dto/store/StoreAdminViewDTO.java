package wd.team4.everycare.dto.store;

import lombok.*;
import wd.team4.everycare.domain.Member;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@ToString
public class StoreAdminViewDTO {
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
    private Member member;

    @Builder
    public StoreAdminViewDTO(Long id, String name, String url, int businessLicenseNumber, String email, String operationStartTime, String operationEndTime, String lunchStartTime, String lunchEndTime, String closedDay, String companyCorporationName, String representativeName, String businessLocation, String customerServiceNumber, LocalDateTime createdAt, Member member) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.businessLicenseNumber = businessLicenseNumber;
        this.email = email;
        this.operationStartTime = operationStartTime;
        this.operationEndTime = operationEndTime;
        this.lunchStartTime = lunchStartTime;
        this.lunchEndTime = lunchEndTime;
        this.closedDay = closedDay;
        this.companyCorporationName = companyCorporationName;
        this.representativeName = representativeName;
        this.businessLocation = businessLocation;
        this.customerServiceNumber = customerServiceNumber;
        this.createdAt = createdAt;
        this.member = member;
    }

}
