package wd.team4.everycare.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "care_note_seq_generator",
        sequenceName = "care_note_seq",
        initialValue = 1, allocationSize = 1)
public class CareNote {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "care_note_seq_generator")
    @Column(name = "care_note_id")
    private Long id;

    @Column(name = "care_note_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "care_note_start_time", length = 5)
    private String startTime;

    @Column(name = "care_note_end_time", length = 5)
    private String endTime;

    @Column(name = "care_note_upload_file_name")
    private String uploadFileName;

    @Column(name = "care_note_store_file_name")
    private String storeFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_sitter_id", nullable = false)
    private CareSitter careSitter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
