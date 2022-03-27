package wd.team4.everycare.service;

import org.springframework.stereotype.Service;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.dto.caretarget.CareTargetDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.CareTargetRepository;
import wd.team4.everycare.service.exception.CareTargetNotFoundException;
import wd.team4.everycare.service.interfaces.CareTargetService;

import java.util.Optional;

@Service
public class CareTargetServiceImpl implements CareTargetService {

    private final CareTargetRepository careTargetRepository;

    public CareTargetServiceImpl(CareTargetRepository careTargetRepository) {
        this.careTargetRepository = careTargetRepository;
    }

    @Override
    public CareTarget dtoToEntity(CareTargetDTO careTargetDTO) {
        return CareTargetService.super.dtoToEntity(careTargetDTO);
    }

    @Override
    public MyResponse<CareTargetDTO> save(CareTargetDTO careTargetDTO) {
        CareTarget careTarget = dtoToEntity(careTargetDTO);
        careTargetRepository.save(careTarget);
        return MyResponse.<CareTargetDTO>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(careTargetDTO)
                .build();
    }

    @Override
    public void removeCareTarget(Long id) {
        careTargetRepository.deleteById(id);
    }

    @Override
    public Long isPresent(Long id) {
        Optional<CareTarget> careTarget = careTargetRepository.findById(id);
        return careTarget.get().getId();
    }

    @Override
    public boolean isEmpty(Long id) {
        if(careTargetRepository.findById(id).isEmpty()){
            return true;
        } throw new CareTargetNotFoundException(id);
    }
}
