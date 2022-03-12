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
@Table(name = "care_sitter_certification")
@SequenceGenerator(name = "care_sitter_certification_seq_generator",
        sequenceName = "care_sitter_certification_seq",
        initialValue = 1, allocationSize = 1)

public class CareSitterCertification {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "care_sitter_certification_seq")
    @Column(name = "care_sitter_certification_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "care_sitter_id")
    private CareSitter careSitter;

    @ManyToOne
    @JoinColumn(name = "certification_id")
    private Certification certification;
}