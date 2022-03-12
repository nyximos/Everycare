package wd.team4.everycare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
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
    private String level;

    @ManyToOne
    @JoinColumn(name = "activity_classification_id2")
    private ActivityClassification activityClassification;


}
