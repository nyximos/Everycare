package wd.team4.everycare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "detail_activity")
@SequenceGenerator(name = "detail_activity_seq_generator",
        sequenceName = "detail_activity_seq",
        initialValue = 1, allocationSize = 1)
public class DetailActivity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detail_activity_seq")
    @Column(name = "detail_activity_id", nullable = false)
    private Long id;

    @Column(name = "detail_activity_rating")
    private int detailActivityRating;

    @Column(name = "detail_activity_review", length = 1000)
    private String detailActivityReview;

    @Column(name = "detailActivityRequirements", length = 500)
    private String detailActivityRequirements;

    @ManyToOne
    @JoinColumn(name = "activity_classification")
    private ActivityClassification activityClassification;

}
