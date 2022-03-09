package wd.team4.everycare;

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
@SequenceGenerator(name = "certification_classification_seq_generator",
        sequenceName = "certification_classification_seq",
        initialValue = 1, allocationSize = 1)
public class CertificationClassification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "certification_classification_seq")
    @Column(name = "certification_classification_id")
    private String id;

    @Column
    private String name;

    private String level;
}
