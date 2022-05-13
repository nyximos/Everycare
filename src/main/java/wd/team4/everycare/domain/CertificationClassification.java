package wd.team4.everycare.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "certification_classification_seq_generator",
        sequenceName = "certification_classification_seq",
        initialValue = 1, allocationSize = 1)
public class CertificationClassification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "certification_classification_seq_generator")
    @Column(name = "certification_classification_id")
    private Long id;

    @Column(name = "certification_classification_name", nullable = false)
    private String name;

    @Column(name = "certification_classification_level", length = 10)
    private String level;

}
