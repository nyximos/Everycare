package wd.team4.everycare.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "health_classification")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "health_classification_seq_generator",
        sequenceName = "health_classification_seq",
        initialValue = 1, allocationSize = 1)
public class HealthClassification {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "health_classification_seq_generator")
    @Column(name = "health_classification_id", nullable = false)
    private Long id;

    @Column(name = "health_classification_name", nullable = false)
    private String name;

}
