package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.CareNote;
import wd.team4.everycare.domain.Contract;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.careNote.CareNoteListDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.CareNoteRepository;
import wd.team4.everycare.repository.ContractRepository;
import wd.team4.everycare.service.interfaces.MemberCareNoteService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCareNoteServiceImpl implements MemberCareNoteService {

    private final CareNoteRepository careNoteRepository;
    private final ContractRepository contractRepository;

    @Override
    public ResponseEntity<MyResponse> getAll(PrincipalDetails principalDetails) {

        Member user = principalDetails.getUser();
        LocalDate today = LocalDate.now();

        List<CareNote> careNotes = careNoteRepository.findAllByMemberAndDate(user, today);
        List<CareNoteListDTO> careNoteListDTOs = new ArrayList<>();

        if(careNotes.isEmpty()) {
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("케어노트 없음!")
                    .build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        }

        for (CareNote careNote : careNotes) {

            Optional<Contract> contract = contractRepository.findById(careNote.getContract().getId());
            Contract contractEntity = contract.orElse(null);

            CareNoteListDTO dto = CareNoteListDTO.builder()
                    .id(careNote.getId())
                    .startTime(contractEntity.getStartTime())
                    .endTime(contractEntity.getEndTime())
                    .careTargetName(contractEntity.getJobOffer().getCareTarget().getName())
                    .build();

            careNoteListDTOs.add(dto);
        }

        MyResponse<List<CareNoteListDTO>> body = MyResponse.<List<CareNoteListDTO>>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(careNoteListDTOs)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);

    }
}