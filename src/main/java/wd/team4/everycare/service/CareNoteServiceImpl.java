package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.CareNote;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.Contract;
import wd.team4.everycare.dto.UploadFile;
import wd.team4.everycare.dto.careNote.CareNoteDetailDTO;
import wd.team4.everycare.dto.careNote.CareNoteImageDTO;
import wd.team4.everycare.dto.careNote.CareNoteListDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.CareNoteRepository;
import wd.team4.everycare.repository.ContractRepository;
import wd.team4.everycare.service.interfaces.CareNoteService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CareNoteServiceImpl implements CareNoteService {

    private final CareNoteRepository careNoteRepository;
    private final ContractRepository contractRepository;
    private final FileStoreService fileStoreService;

    @Override
    public ResponseEntity<MyResponse> getAll(PrincipalDetails principalDetails) {

        CareSitter careSitter = principalDetails.getCareSitter();
        LocalDate today = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

        List<CareNote> careNotes = careNoteRepository.findAllByCareSitterAndDate(careSitter, today);
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
                    .careTargetName(contractEntity.getCareTarget().getName())
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

    @Override
    public ResponseEntity<MyResponse> get(Long id) {

        Optional<CareNote> careNote = careNoteRepository.findById(id);
        CareNote careNoteEntity = careNote.orElse(null);

        CareNoteDetailDTO careNoteDetailDTO = CareNoteDetailDTO.builder()
                .id(careNoteEntity.getId())
                .build();

        if(careNoteEntity.getStartTime()!=null){
            careNoteDetailDTO.setStartTime(careNoteEntity.getStartTime());
            careNoteDetailDTO.setStoreFileName(careNoteDetailDTO.getStoreFileName());
        }


        MyResponse<CareNoteDetailDTO> body = MyResponse.<CareNoteDetailDTO>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(careNoteDetailDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> start(Long id, CareNoteImageDTO imageDTO) throws IOException {

        Optional<CareNote> careNote = careNoteRepository.findById(id);
        CareNote careNoteEntity = careNote.orElse(null);

        UploadFile attachFile = fileStoreService.storeFile(imageDTO.getAttachFile());

        careNoteEntity.saveImage(attachFile);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }
}
