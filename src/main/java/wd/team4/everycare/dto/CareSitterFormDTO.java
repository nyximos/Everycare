package wd.team4.everycare.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CareSitterFormDTO {

    private String preferredType;
    private String desiredDayWeek;
    private String activityTime;
    private String desiredHourlyWage;
    private String desiredMonthlyWage;
    private int cctvAgreement;
    private int vaccination;
    private String introduction;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String memberId;
    private MultipartFile attachFile;
    private List<MultipartFile> attachFiles;
    //private UploadFile uploadFile;
    //private List<UploadFile> uploadFiles;

    public void createTime(LocalDateTime time) {
        this.createdAt = time;
    }
    public void updateTime(LocalDateTime time){
        this.updatedAt = time;
    }

    //public void uploadAttachFile(UploadFile uploadFile) { this.uploadFile = uploadFile; }
    //public void uploadImageFiles(List<UploadFile> uploadFiles) { this.uploadFiles = uploadFiles; }

    @Builder(builderMethodName = "careSitterDTOBuilder")
    public CareSitterFormDTO(String preferredType, String desiredDayWeek, String activityTime, String desiredHourlyWage, String desiredMonthlyWage, int cctvAgreement, int vaccination, String introduction, LocalDateTime createdAt, LocalDateTime updatedAt, String memberId) {
        this.preferredType = preferredType;
        this.desiredDayWeek = desiredDayWeek;
        this.activityTime = activityTime;
        this.desiredHourlyWage = desiredHourlyWage;
        this.desiredMonthlyWage = desiredMonthlyWage;
        this.cctvAgreement = cctvAgreement;
        this.vaccination = vaccination;
        this.introduction = introduction;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.memberId = memberId;
    }

    @Builder(builderMethodName = "careSitterImageDTOBuilder")
    public CareSitterFormDTO(MultipartFile attachFile, List<MultipartFile> attachFiles) {
        this.attachFile = attachFile;
        this.attachFiles = attachFiles;
    }
}
