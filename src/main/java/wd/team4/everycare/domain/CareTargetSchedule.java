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
@Table(name = "care_target_schedule")
@SequenceGenerator(name = "care_target_schedule_seq_generator",
        sequenceName = "care_target_schedule_seq",
        initialValue = 1, allocationSize = 1)
public class CareTargetSchedule {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "care_target_schedule_seq_generator")
    @Column(name = "care_target_schedule_id", nullable = false)
    private Long id;

    @Column(name = "care_target_schedule_name", length = 50, nullable = false)
    private String name;

    @Column(name = "care_target_schedule_start_time", length = 4, nullable = false)
    private String startTime;

    @Column(name = "care_target_schedule_end_time", length = 4, nullable = false)
    private String endTime;

}
