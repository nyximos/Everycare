package wd.team4.everycare.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "care_sitter_review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "care_sitter_review_seq_generator",
        sequenceName = "care_sitter_review_seq",
        initialValue = 1, allocationSize = 1)
public class CareSitterActivity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "care_sitter_activity_seq")
    @Column(name = "care_sitter_review_id", nullable = false)
    private Long id;

    @Column(name = "care_sitter_review_rating")
    private int rating;

    @Column(name = "care_sitter_review_content", length = 1000)
    private String comment;

    @Column(name = "care_sitter_review_created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_classification", nullable = false)
    private ActivityClassification activityClassification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_sitter_id")
    private CareSitter careSitter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_target_schedule_id")
    private CareTargetSchedule careTargetSchedule;

}
