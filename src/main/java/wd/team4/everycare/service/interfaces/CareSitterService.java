package wd.team4.everycare.service.interfaces;

import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.CareSitterImage;
import wd.team4.everycare.dto.careSitter.CareSitterFormDTO;
import wd.team4.everycare.dto.UploadFile;

import java.io.IOException;
import java.util.List;

public interface CareSitterService {

    Long save(CareSitterFormDTO dto) throws IOException;

    CareSitter isPresent(Long id);

    boolean isEmpty(String id);

    CareSitter findCareSitter(String id);

    List<CareSitterImage> findCareSitterImages(Long id);

    String update(Long id, CareSitterFormDTO careSitterFormDTO);

    default CareSitterImage careSitterDtoToImage(CareSitter careSitter, UploadFile attachFile) throws IOException {
        return CareSitterImage.builder()
                .uploadFileName(attachFile.getUploadFileName())
                .storeFileName(attachFile.getStoreFileName())
                .careSitter(careSitter)
                .build();
    }
}
