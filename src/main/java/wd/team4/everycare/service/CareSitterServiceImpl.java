package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.dto.CareSitterDTO;
import wd.team4.everycare.repository.CareSitterRepository;
import wd.team4.everycare.service.interfaces.CareSitterService;

@Service
@RequiredArgsConstructor
public class CareSitterServiceImpl implements CareSitterService {

    private final CareSitterRepository careSitterRepository;

    @Override
    public Long register(CareSitterDTO dto) {
        CareSitter careSitter = dtoToEntity(dto);
        careSitterRepository.save(careSitter);
        return careSitter.getId();
    }

    @Override
    public CareSitter dtoToEntity(CareSitterDTO dto) {
        return CareSitter.builder()
                .preferredType(dto.getPreferredType())
                .desiredDayWeek(dto.getDesiredHourlyWage())
                .activityTime(dto.getActivityTime())
                .desiredHourlyWage(dto.getDesiredHourlyWage())
                .desiredMonthlyWage(dto.getDesiredMonthlyWage())
                .cctvAgreement(dto.getCctvAgreement())
                .vaccination(dto.getVaccination())
                .introduction(dto.getIntroduction())
                .disclosureStatus(1)
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }

}
