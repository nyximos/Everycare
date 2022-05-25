package wd.team4.everycare.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "care_sitter_review_seq_generator",
        sequenceName = "care_sitter_review_seq",
        initialValue = 1, allocationSize = 1)
public class CareSitterReview {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "care_sitter_review_seq_generator")
    @Column(name = "care_sitter_review_id", nullable = false)
    private Long id;

    @Column(name = "care_sitter_review_rating", nullable = false)
    private Long rating;

    @Column(name = "care_sitter_review_comment", length = 1000, nullable = false)
    private Long comment;

    @Column(name = "care_sitter_review_createdAt", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_classification_id")
    private ActivityClassification activityClassification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_sitter_id")
    private CareSitter careSitter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_target_schedule_id")
    private CareTargetSchedule careTargetSchedule;
}
