package wd.team4.everycare.service.interfaces;

import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.CareSitterImage;
import wd.team4.everycare.dto.CareSitterFormDTO;
import wd.team4.everycare.dto.UploadFile;

import java.io.IOException;
import java.util.List;

public interface CareSitterService {

    Long save(CareSitterFormDTO dto) throws IOException;

    default CareSitterImage careSitterDtoToImage(CareSitter careSitter, UploadFile attachFile) throws IOException {
        return CareSitterImage.builder()
                .uploadFileName(attachFile.getUploadFileName())
                .storeFileName(attachFile.getStoreFileName())
                .careSitter(careSitter)
                .build();
    }

    default CareSitter careSitterDtoToCareSitter(CareSitterFormDTO dto){
        CareSitter careSitter = CareSitter.builder()
                .preferredType(dto.getPreferredType())
                .desiredDayWeek(dto.getDesiredDayWeek())
                .activityTime(dto.getActivityTime())
                .desiredHourlyWage(dto.getDesiredHourlyWage())
                .desiredMonthlyWage(dto.getDesiredMonthlyWage())
                .cctvAgreement(dto.getCctvAgreement())
                .cctvAgreement(dto.getVaccination())
                .introduction(dto.getIntroduction())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
        return careSitter;
    }


    void removeCareSitter(Long id);

    CareSitter isPresent(Long id);

    boolean isEmpty(String id);

    List<CareSitterImage> findCareSitterImages(Long id);
}
