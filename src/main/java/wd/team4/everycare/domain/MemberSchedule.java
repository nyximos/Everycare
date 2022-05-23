package wd.team4.everycare.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import wd.team4.everycare.dto.member.MemberScheduleFormDTO;
import wd.team4.everycare.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "member_schedule_seq_generator",
        sequenceName = "member_schedule_seq",
        initialValue = 1, allocationSize = 1)
public class MemberSchedule {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_schedule_seq_generator")
    @Column(name = "member_schedule_id", length = 30)
    private Long id;

    @Column(name = "member_schedule_name", nullable = false)
    private String name;

    @Column(name = "member_schedule_start_datetime", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime startDatetime;

    @Column(name = "member_schedule_end_datetime", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime endDatetime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name = "member_schedule_alarm_time")
    private LocalDateTime alarmTime;

    @Column(name = "member_schedule_address_name")
    private String addressName;

    @Column(name = "member_schedule_comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public void saveAlarmTime(LocalDateTime alarmTime) {
        this.alarmTime = alarmTime;
    }

    public void saveAddressName(String addressName) {
        this.addressName = addressName;
    }

    public void saveComment(String comment) {
        this.comment = comment;
    }

    public void update(MemberScheduleFormDTO memberScheduleFormDTO) {
        if(StringUtils.isNotBlank(memberScheduleFormDTO.getName())) {
            this.name = memberScheduleFormDTO.getName();
        }
        if(StringUtils.isNotBlank(String.valueOf(memberScheduleFormDTO.getStartDatetime()))) {
            this.startDatetime = memberScheduleFormDTO.getStartDatetime();
        }
        if(StringUtils.isNotBlank(String.valueOf(memberScheduleFormDTO.getEndDatetime()))) {
            this.endDatetime = memberScheduleFormDTO.getEndDatetime();
        }
        if(StringUtils.isNotBlank(String.valueOf(memberScheduleFormDTO.getAlarmTime()))) {
            this.alarmTime = memberScheduleFormDTO.getAlarmTime();
        }
        if(StringUtils.isNotBlank(memberScheduleFormDTO.getAddressName())) {
            this.addressName = memberScheduleFormDTO.getAddressName();
        }
        if(StringUtils.isNotBlank(memberScheduleFormDTO.getName())) {
            this.comment = memberScheduleFormDTO.getComment();
        }
    }
}