package wd.team4.everycare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "member_schedule_seq_generator",
        sequenceName = "member_schedule_seq",
        initialValue = 1, allocationSize = 1)
public class MemberSchedule {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_schedule_seq")
    @Column(name = "member_schedule_id", length = 30)
    private Long id;

    @Column(name = "member_schedule_name", nullable = false)
    private String name;

    @Column(name = "member_schedule_start_datetime", nullable = false)
    private LocalDateTime startDatetime;

    @Column(name = "member_schedule_end_datetime", nullable = false)
    private LocalDateTime endDatetime;

    @Column(name = "member_schedule_alarm_time")
    private LocalDateTime alarmTime;

    @Column(name = "member_schedule_address_name")
    private String addressName;

    @Column(name = "member_schedule_comment")
    private String comment;

    /**
     * TODO 회원id 외래키 연결결
     **/
}
