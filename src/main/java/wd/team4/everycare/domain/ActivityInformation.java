package wd.team4.everycare.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import wd.team4.everycare.dto.careTargetSchedule.ActivityInformationListViewDTO;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "activity_information")
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
    @JoinColumn(name = "care_target_schedule_id")
    private CareTargetSchedule careTargetSchedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_classification_id")
    private ActivityClassification activityClassification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_sitter_activity_id")
    private CareSitterActivity careSitterActivity;

    public ActivityInformationListViewDTO toListViewDTO(){
        return ActivityInformationListViewDTO.builder()
                .id(this.id)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .requirement(this.requirement)
                .activityClassificationDTO(this.activityClassification.toDTO())
                .build();
    }
}
