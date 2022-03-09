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
@SequenceGenerator(name = "certification_seq_generator",
        sequenceName = "certification_seq",
        initialValue = 1, allocationSize = 1)

public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "certification_seq")
    @Column(name = "certification_id")
    private String id;

    @Column(name = "certification_name")
    private String name;

    @Column(name = "certification_file_name", nullable = false)
    private String fileName;

    @Column(name = "certification_file_path", nullable = false)
    private String filePath;

    @Column(name = "certification_is_approve", length = 5, nullable = false)
    private String approvement;
}
