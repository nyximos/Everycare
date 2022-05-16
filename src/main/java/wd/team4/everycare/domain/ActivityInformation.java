package wd.team4.everycare.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import wd.team4.everycare.dto.careTargetSchedule.ActivityInformationFormDTO;
import wd.team4.everycare.dto.careTargetSchedule.ActivityInformationViewDTO;
import wd.team4.everycare.util.StringUtils;

import javax.persistence.*;

@Getter
@Entity
@Builder
@DynamicUpdate
@AllArgsConstructor
@Table(name = "activity_information")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "activity_information_seq_generator",
        sequenceName = "activity_information_seq",
        initialValue = 1, allocationSize = 1)
public class ActivityInformation {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_information_seq_generator")
    @Column(name = "activity_information_id", nullable = false)
    private Long id;

    @Column(name = "activity_information_start_time", nullable = false)
    private String startTime;

    @Column(name = "activity_information_end_time", nullable = false)
    private String endTime;

    @Column(name = "activity_information_requirement", length = 500)
    private String requirement;

    @Column(name = "activity_information_content", length = 255)
    private String content;

    @Column(name = "activity_information_upload_file_name", length = 255)
    private String uploadFileName;

    @Column(name = "activity_information_store_file_name", length = 255)
    private String storeFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_classification_id")
    private ActivityClassification activityClassification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_target_schedule_id")
    private CareTargetSchedule careTargetSchedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_sitter_activity_id")
    private CareSitterActivity careSitterActivity;

    @Override
    public String toString() {
        return "ActivityInformation{" +
                "id=" + id +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", requirement='" + requirement + '\'' +
                ", content='" + content + '\'' +
                ", uploadFileName='" + uploadFileName + '\'' +
                ", storeFileName='" + storeFileName + '\'' +
                '}';
    }

    public ActivityInformationViewDTO toListViewDTO(){
        return ActivityInformationViewDTO.builder()
                .id(this.id)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .requirement(this.requirement)
                .activityClassificationDTO(this.activityClassification.toDTO())
                .build();
    }

    @Builder
    public ActivityInformation(String startTime, String endTime, String requirement) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.requirement = requirement;
    }

    public void save(ActivityClassification activityClassification, CareTargetSchedule careTargetSchedule){
        this.activityClassification = activityClassification;
        this.careTargetSchedule = careTargetSchedule;
    }


    public void update(ActivityInformationFormDTO activityInformationFormDTO) {

        if(StringUtils.isNotBlank(activityInformationFormDTO.getStartTime())) {
            this.startTime = activityInformationFormDTO.getStartTime();
        }
        if(StringUtils.isNotBlank(activityInformationFormDTO.getEndTime())) {
            this.endTime = activityInformationFormDTO.getEndTime();
        }
        if(StringUtils.isNotBlank(activityInformationFormDTO.getRequirement())) {
            this.requirement = activityInformationFormDTO.getRequirement();
        }
    }
}
