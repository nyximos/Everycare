package wd.team4.everycare.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "report_seq_generator",
        sequenceName = "report_seq",
        initialValue = 1, allocationSize = 1)
public class Report {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_seq_generator")
    @Column(name = "report_id")
    private Long id;

    @Column(name = "report_created_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "report_type", length = 100, nullable = false)
    private String type;

    @Column(name = "report_status")
    private int status;

    @Column(name = "report_reason", length = 500, nullable = false)
    private String reason;

    @Column(name = "report_reported_user_id", length = 20, nullable = false)
    private String reportedUserId;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;
}
