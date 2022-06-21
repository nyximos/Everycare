package wd.team4.everycare.dto.jobOffer_jobSearch;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.domain.Gender;
import wd.team4.everycare.dto.careTargetSchedule.CareTargetScheduleListDTO;
import wd.team4.everycare.dto.member.MemberListViewDTO;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class JobOfferListDTO {

    private Long id;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;                        ////시작일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;                          ////종료일
    private String day;                      ////희망 요일
    private Gender desiredCareSitterGender;             ////희망 케어시터 성별
    private int pay;                                    ////급여
    private int amount;
    private String comment;                             ////코멘트
    private String desiredStartTime;                    //시작시간
    private String desiredEndTime;                      //종료시간
    private MemberListViewDTO member;
    private JobOfferCareTargetDTO careTarget;                      //케어 대상인
    private CareTargetScheduleListDTO careTargetSchedule;      //스케줄
    private String careTargetImage;
}
