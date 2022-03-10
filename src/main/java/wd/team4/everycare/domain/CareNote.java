package wd.team4.everycare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "care_note_seq_generator",
        sequenceName = "care_note_seq",
        initialValue = 1, allocationSize = 1)
public class CareNote {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "care_note_seq")
    private Long id;

    @Column(name = "care_note_date")
    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate noteDate;

    @Column(name = "care_note_start_time", length = 4)
    private String startTime;

    @Column(name = "care_note_end_time", length = 4)
    private String endTime;

    @Column(name = "care_note_file_name")
    private String fileName;

    @Column(name = "care_note_file_path")
    private String filePath;

    @OneToMany
    @JoinColumn(name = "contract_id", nullable = false)
    private List<Contract> contracts;

    @OneToMany
    @JoinColumn(name = "care_sitter_profile_id", nullable = false)
    private List<CareSitter> careSitters;

    /**
     * TODO 회원Id 외래키 연결
     * */
}
