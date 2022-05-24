package wd.team4.everycare.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import wd.team4.everycare.dto.careTargetSchedule.CareTargetScheduleDTO;
import wd.team4.everycare.dto.careTargetSchedule.CareTargetScheduleListDTO;
import wd.team4.everycare.util.StringUtils;

import javax.persistence.*;

@Getter
@Entity
@Builder
@DynamicUpdate
@AllArgsConstructor
@Table(name = "care_target_schedule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "care_target_schedule_seq_generator",
        sequenceName = "care_target_schedule_seq",
        initialValue = 1, allocationSize = 1)
@ToString
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_sitter_id")
    private CareSitter careSitter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_note_id")
    private CareNote careNote;


    @Builder
    public CareTargetSchedule(Long id, String name, String startTime, String endTime, CareTarget careTarget) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.careTarget = careTarget;
    }

    public CareTargetScheduleDTO toDTO(){
        return CareTargetScheduleDTO.builder()
                .id(this.id)
                .name(this.name)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .build();
    }

    public CareTargetScheduleListDTO toListDTO(){
        return CareTargetScheduleListDTO.builder()
                .id(this.id)
                .name(this.name)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .build();
    }

    public void saveCareTarget(CareTarget careTarget){
        this.careTarget = careTarget;
    }

    public void update(CareTargetScheduleDTO careTargetScheduleDTO) {

        if(StringUtils.isNotBlank(careTargetScheduleDTO.getName())) {
            this.name = careTargetScheduleDTO.getName();
        }
        if(StringUtils.isNotBlank(careTargetScheduleDTO.getStartTime())) {
            this.startTime = careTargetScheduleDTO.getStartTime();
        }
        if(StringUtils.isNotBlank(careTargetScheduleDTO.getEndTime())) {
            this.endTime = careTargetScheduleDTO.getEndTime();
        }
    }
}
