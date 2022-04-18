package wd.team4.everycare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "health_record")
@SequenceGenerator(name = "health_record_seq_generator",
        sequenceName = "health_record_seq",
        initialValue = 1, allocationSize = 1)
public class HealthRecord {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "health_record_seq_generator")
    @Column(name = "health_record_id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "health_record_status", nullable = false)
    private HealthStatus healthStatus;

    @Column(name = "health_record_detail_comment")
    private String detailComment;

    @Column(name = "health_record_created_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "health_record_updated_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "health_information_id")
    private HealthInformation healthInformation;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "care_target_id")
    private CareTarget careTarget;

}
