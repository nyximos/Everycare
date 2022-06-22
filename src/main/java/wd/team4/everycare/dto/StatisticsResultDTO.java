package wd.team4.everycare.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StatisticsResultDTO {

    public List<LocalDateTime> time = new ArrayList<>();
    public List<Integer> amount = new ArrayList<>();

}
