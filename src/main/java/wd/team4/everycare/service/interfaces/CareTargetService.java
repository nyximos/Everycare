package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.CareTargetImage;
import wd.team4.everycare.dto.caretarget.CareTargetFormDTO;
import wd.team4.everycare.dto.caretarget.CareTargetViewDTO;
import wd.team4.everycare.dto.UploadFile;
import wd.team4.everycare.dto.response.MyResponse;

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

    String update(Long id, CareTargetFormDTO careTargetFormDTO);

    CareTargetViewDTO webFindCareTarget(Long id);

    ResponseEntity<MyResponse> findAll(PrincipalDetails principalDetails);

    ResponseEntity<MyResponse> findById(Long id);
}
