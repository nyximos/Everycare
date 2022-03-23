package wd.team4.everycare.service.interfaces;

import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.dto.CareSitterDTO;

public interface CareSitterService {

    Long register(CareSitterDTO dto);

    default CareSitter dtoToEntity(CareSitterDTO dto){
        CareSitter careSitter = CareSitter.builder()
                .preferredType(dto.getPreferredType())
                .desiredDayWeek(dto.getDesiredDayWeek())
                .activityTime(dto.getActivityTime())
                .desiredHourlyWage(dto.getDesiredHourlyWage())
                .desiredMonthlyWage(dto.getDesiredMonthlyWage())
                .cctvAgreement(dto.getCctvAgreement())
                .cctvAgreement(dto.getVaccination())
                .introduction(dto.getIntroduction())
//                .createdAt(dto.getCreatedAt())
//                .updatedAt(dto.getUpdatedAt())
                .build();
        return careSitter;
    }

    void removeCareSitter(Long id);

    String isPresent(String id);

    boolean isEmpty(String id);

}
