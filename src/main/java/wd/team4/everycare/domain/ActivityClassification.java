package wd.team4.everycare.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import wd.team4.everycare.dto.careTargetSchedule.ActivityClassificationDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@DynamicUpdate
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "activity_classification")
@SequenceGenerator(name = "activity_classification_seq_generator",
        sequenceName = "activity_classification_seq",
        initialValue = 1, allocationSize = 1)
public class ActivityClassification {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_classification_seq_generator")
    @Column(name = "activity_classification_id", nullable = false)
    private Long id;

    @Column(name = "activity_classification_name", length = 50, nullable = false)
    private String name;

    @Column(name = "activity_classification_level", length = 10, nullable = false)
    private int level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_classification_parent")
    private ActivityClassification activityClassification;

    public ActivityClassificationDTO toDTO(){
        return ActivityClassificationDTO.builder()
                .id(this.id)
                .name(this.name)
                .level(this.level)
                .build();
    }
}
