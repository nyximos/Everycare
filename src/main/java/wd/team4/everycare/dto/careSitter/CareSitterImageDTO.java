package wd.team4.everycare.dto.careSitter;

import lombok.*;
import wd.team4.everycare.domain.CareSitterImage;

@Getter
@Setter
@Builder
public class CareSitterImageDTO {
    private Long id;
    private String uploadFileName;
    private String storeFileName;

    public CareSitterImage toCareSitterImageDTO(){
        return CareSitterImage.builder()
                .storeFileName(this.storeFileName)
                .uploadFileName(this.uploadFileName)
                .build();
    }
}
