package wd.team4.everycare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "report_seq_generator",
        sequenceName = "report_seq",
        initialValue = 1, allocationSize = 1)
public class Report {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_seq")
    @Column(name = "report_id")
    private Long id;

    @Column(name = "report_created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "report_type", length = 100, nullable = false)
    private String type;

    @Column(name = "report_processing_status")
    private int processingStatus;

    @Column(name = "report_reason", nullable = false)
    private String reason;

    @Column(name = "report_reported_person", length = 20, nullable = false)
    private String reportedPerson;

    @OneToMany
    @JoinColumn(name = "board_id", nullable = false)
    private List<Board> boards;

    @OneToMany
    @JoinColumn(name = "member_id", nullable = false)
    private List<Member> members;
}
