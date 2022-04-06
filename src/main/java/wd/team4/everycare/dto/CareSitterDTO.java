package wd.team4.everycare.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wd.team4.everycare.domain.CareSitter;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CareSitterDTO {
    private String preferredType;
    private String desiredDayWeek;
    private String activityTime;
    private String desiredHourlyWage;
    private String desiredMonthlyWage;
    private int cctvAgreement;
    private int vaccination;
    private String introduction;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String memberId;

    public void createTime(LocalDateTime time) {
        this.createdAt = time;
    }

    public void updateTime(LocalDateTime time){
        this.updatedAt = time;
    }

}
