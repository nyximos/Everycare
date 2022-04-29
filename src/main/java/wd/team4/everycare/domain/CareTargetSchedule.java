package wd.team4.everycare.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import wd.team4.everycare.dto.CareTargetScheduleDTO;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
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

    @Column(name = "care_target_schedule_start_time", length = 5, nullable = false)
    private String startTime;

    @Column(name = "care_target_schedule_end_time", length = 5, nullable = false)
    private String endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_target_id")
    private CareTarget careTarget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    private Contract contract;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "care_sitter_id")
//    private CareSitter careSitter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_note_id")
    private CareNote careNote;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_offer_id")
    private JobOffer jobOffer;

    @Builder
    public CareTargetSchedule(Long id, String name, LocalTime startTime, LocalTime endTime, CareTarget careTarget) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.careTarget = careTarget;
    }

    public CareTargetScheduleDTO toDTO(){
        return CareTargetScheduleDTO.builder()
                .name(this.name)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .build();
    }

}
