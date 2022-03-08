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
@SequenceGenerator(name = "bedge_seq_generator",
        sequenceName = "bedge_seq",
        initialValue = 1, allocationSize = 1)
public class Bedge {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bedge_seq_generator")
    @Column(name = "bedge_id", nullable = false)
    private Long id;

    @Column(name = "bedge_file_name", length = 255, nullable = false)
    private String fileName;

    @Column(name = "bedge_file_path", length = 255, nullable = false)
    private String filePath;
}
