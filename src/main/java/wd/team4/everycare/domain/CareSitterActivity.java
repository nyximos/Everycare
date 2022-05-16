package wd.team4.everycare.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "detail_activity")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "care_sitter_activity_seq_generator",
        sequenceName = "care_sitter_activity_seq",
        initialValue = 1, allocationSize = 1)
public class CareSitterActivity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "care_sitter_activity_seq")
    @Column(name = "care_sitter_activity_id", nullable = false)
    private Long id;

    @Column(name = "care_sitter_activity_rating")
    private int detailActivityRating;

    @Column(name = "care_sitter_activity_review", length = 1000)
    private String detailActivityReview;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_classification2", nullable = false)
    private ActivityClassification activityClassification;

}
