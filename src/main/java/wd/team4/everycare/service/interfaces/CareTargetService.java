package wd.team4.everycare.service.interfaces;

import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.dto.caretarget.CareTargetDTO;
import wd.team4.everycare.dto.response.MyResponse;

public interface CareTargetService {

    default CareTarget dtoToEntity(CareTargetDTO dto) {
        CareTarget careTarget = CareTarget.builder()
                .name(dto.getName())
                .gender(dto.getGender())
                .birth(dto.getBirth())
                .height(dto.getHeight())
                .weight(dto.getWeight())
                .zipcode(dto.getZipcode())
                .address(dto.getAddress())
                .detailedAddress(dto.getDetaildAddress())
                .longTermCareGrade(dto.getLongTermCareGrade())
                .comment(dto.getComment())
                .pet(dto.getPet())
                .isCctvAgreement(dto.getIsCctvAgrement())
                .careType(dto.getCareType())
                .coronaTest(dto.getCoronaTest())
                .member(dto.getMember())
                .build();
        return careTarget;
    }

    MyResponse<CareTargetDTO> save(CareTargetDTO dto);

    void removeCareTarget(Long id);

    Long isPresent(Long id);

    boolean isEmpty(Long id);

}
