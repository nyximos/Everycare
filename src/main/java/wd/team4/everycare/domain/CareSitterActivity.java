package wd.team4.everycare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "detail_activity")
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

    @ManyToOne
    @JoinColumn(name = "activity_classification2", nullable = false)
    private ActivityClassification activityClassification;

}
