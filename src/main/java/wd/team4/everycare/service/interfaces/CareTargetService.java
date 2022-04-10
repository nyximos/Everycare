package wd.team4.everycare.service.interfaces;

import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.CareTargetImage;
import wd.team4.everycare.dto.CareTargetFormDTO;
import wd.team4.everycare.dto.UploadFile;

import java.io.IOException;
import java.util.List;

public interface CareTargetService {

    default CareTarget careTargetDtoToEntity(CareTargetFormDTO careTargetFormDTO) {
        CareTarget careTarget = CareTarget.builder()
                .name(careTargetFormDTO.getName())
                .gender(careTargetFormDTO.getGender())
                .birth(careTargetFormDTO.getBirth())
                .height(careTargetFormDTO.getHeight())
                .weight(careTargetFormDTO.getWeight())
                .zipcode(careTargetFormDTO.getZipcode())
                .address(careTargetFormDTO.getAddress())
                .detailedAddress(careTargetFormDTO.getDetailedAddress())
                .longTermCareGrade(careTargetFormDTO.getLongTermCareGrade())
                .comment(careTargetFormDTO.getComment())
                .pet(careTargetFormDTO.getPet())
                .isCctvAgreement(careTargetFormDTO.getIsCctvAgreement())
                .careType(careTargetFormDTO.getCareType())
                .coronaTest(careTargetFormDTO.getCoronaTest())
                .member(careTargetFormDTO.getMember())
                .build();
        return careTarget;
    }

    default CareTargetImage careTargetDtoToImage(CareTarget careTarget, UploadFile attachFile) throws IOException{
        return CareTargetImage.builder()
                .uploadFileName(attachFile.getUploadFileName())
                .storeFileName(attachFile.getStoreFileName())
                .careTarget(careTarget)
                .build();
    }

    Long save(CareTargetFormDTO careTargetFormDTO) throws IOException;

    void removeCareTarget(Long id);

    CareTarget isPresent(Long id);

    boolean isEmpty(Long id);

    List<CareTarget> findCareTargets(String id);

    List<CareTargetImage> findCareTargetImages(Long id);

}
