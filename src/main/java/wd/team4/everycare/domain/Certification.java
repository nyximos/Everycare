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
@SequenceGenerator(name = "certification_seq_generator",
        sequenceName = "certification_seq",
        initialValue = 1, allocationSize = 1)

public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "certification_seq")
    @Column(name = "certification_id")
    private Long id;

    @Column(name = "certification_name")
    private String name;

    @Column(name = "certification_file_name", nullable = false)
    private String fileName;

    @Column(name = "certification_file_path", nullable = false)
    private String filePath;

    @Column(name = "certification_is_approve", nullable = false)
    private int approvement;

    @ManyToOne
    @JoinColumn(name = "certificate_classification_id", nullable = false)
    private Certification certification;

    @OneToMany
    @JoinColumn(name = "certification_id", nullable = false)
    private List<CareSitterCertification> careSitterCertifications = new ArrayList<>();
}
