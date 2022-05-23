package wd.team4.everycare.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.dto.careRecord.HealthRecordFormDTO;
import wd.team4.everycare.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "health_record")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "health_record_seq_generator",
        sequenceName = "health_record_seq",
        initialValue = 1, allocationSize = 1)
public class HealthRecord {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "health_record_seq_generator")
    @Column(name = "health_record_id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "health_record_status", nullable = false)
    private HealthStatus healthStatus;

    @Column(name = "health_record_detail_comment")
    private String detailComment;

    @Column(name = "health_record_created_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "health_record_updated_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_classification_id")
    private HealthClassification healthClassification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_target_id")
    private CareTarget careTarget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_note_id")
    private CareNote careNote;

    public void saveCategory(HealthClassification healthClassificationEntity) {
        this.healthClassification = healthClassificationEntity;
    }

    public void update(HealthRecordFormDTO healthRecordFormDTO) {
        if(StringUtils.isNotBlank(String.valueOf(healthRecordFormDTO.getHealthStatus()))) {
            this.healthStatus = healthRecordFormDTO.getHealthStatus();
        }
        if(StringUtils.isNotBlank(healthRecordFormDTO.getDetailComment())){
            this.detailComment = healthRecordFormDTO.getDetailComment();
        }
        this.updatedAt = LocalDateTime.now();
    }

    public void saveDetailComment(String detailComment) {
        this.detailComment = detailComment;
    }
}