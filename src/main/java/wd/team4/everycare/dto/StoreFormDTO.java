package wd.team4.everycare.dto;

import lombok.*;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.domain.Store;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StoreFormDTO {
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
    private Member member;

    @Builder
    public StoreFormDTO(String name, String url, int businessLicenseNumber, String email, String operationStartTime, String operationEndTime, String lunchStartTime, String lunchEndTime, String closedDay, String companyCorporationName, String representativeName, String businessLocation, String customerServiceNumber, Member member) {
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
        this.member = member;
    }

    public Store toStore(){
        return Store.builder()
                .id(this.id)
                .name(this.name)
                .url(this.url)
                .businessLicenseNumber(this.businessLicenseNumber)
                .email(this.email)
                .operationStartTime(this.operationStartTime)
                .operationEndTime(this.operationEndTime)
                .lunchStartTime(this.lunchStartTime)
                .lunchEndTime(this.lunchEndTime)
                .closedDay(this.closedDay)
                .companyCorporationName(this.companyCorporationName)
                .representativeName(this.representativeName)
                .businessLocation(this.businessLocation)
                .customerServiceNumber(this.customerServiceNumber)
                .member(this.member)
                .build();
    }
}
