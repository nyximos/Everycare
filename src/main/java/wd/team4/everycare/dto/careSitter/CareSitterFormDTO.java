package wd.team4.everycare.dto.careSitter;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.Member;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CareSitterFormDTO {

    private String name;
    private String preferredType;
    private String hopefulRegion;
    private String desiredDayWeek;
    private String activityTime;
    private String desiredHourlyWage;
    private String desiredMonthlyWage;
    private int cctvAgreement;
    private int vaccination;
    private String introduction;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Member member;
    private List<MultipartFile> attachFiles;
    private int disclosureStatus;

    @Builder(builderMethodName = "careSitterDTOBuilder")
    public CareSitterFormDTO(String preferredType, String hopefulRegion, String desiredDayWeek, String activityTime, String desiredHourlyWage, String desiredMonthlyWage, int cctvAgreement, int vaccination, String introduction, LocalDateTime createdAt, LocalDateTime updatedAt, Member member) {
        this.preferredType = preferredType;
        this.hopefulRegion = hopefulRegion;
        this.desiredDayWeek = desiredDayWeek;
        this.activityTime = activityTime;
        this.desiredHourlyWage = desiredHourlyWage;
        this.desiredMonthlyWage = desiredMonthlyWage;
        this.cctvAgreement = cctvAgreement;
        this.vaccination = vaccination;
        this.introduction = introduction;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.member = member;
    }

    @Builder(builderMethodName = "careSitterImageDTOBuilder")
    public CareSitterFormDTO(List<MultipartFile> attachFiles) {
        this.attachFiles = attachFiles;
    }

    public CareSitter toCareSitter() {
        return CareSitter.builder()
                .name(this.name)
                .preferredType(this.preferredType)
                .hopefulRegion(this.hopefulRegion)
                .desiredDayWeek(this.desiredDayWeek)
                .activityTime(this.activityTime)
                .desiredHourlyWage(this.desiredHourlyWage)
                .desiredMonthlyWage(this.desiredMonthlyWage)
                .cctvAgreement(this.cctvAgreement)
                .vaccination(this.vaccination)
                .introduction(this.introduction)
                .disclosureStatus(1)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .member(this.member)
                .build();
    }

}
