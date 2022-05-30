package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.*;
import wd.team4.everycare.dto.UploadFile;
import wd.team4.everycare.dto.careNote.*;
import wd.team4.everycare.dto.contract.CareSitterCompletionContractDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.*;
import wd.team4.everycare.repository.query.CareNoteQueryRepository;
import wd.team4.everycare.service.interfaces.CareSitterCareNoteService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CareSitterCareNoteServiceImpl implements CareSitterCareNoteService {

    private final CareNoteRepository careNoteRepository;
    private final ContractRepository contractRepository;
    private final FileStoreService fileStoreService;
    private final CareTargetScheduleRepository careTargetScheduleRepository;
    private final ActivityInformationRepository activityInformationRepository;
    private final CareTargetImageRepository careTargetImageRepository;
    private final CareNoteQueryRepository careNoteQueryRepository;

    @Override
    public ResponseEntity<MyResponse> getAll(PrincipalDetails principalDetails) {

        CareSitter careSitter = principalDetails.getCareSitter();
        LocalDate today = LocalDate.now();

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

            CareTarget careTarget = contractEntity.getJobOffer().getCareTarget();
            List<CareTargetImage> images = careTargetImageRepository.findAllByCareTarget(careTarget);

            CareNoteListDTO dto = CareNoteListDTO.builder()
                    .id(careNote.getId())
                    .startTime(contractEntity.getStartTime())
                    .endTime(contractEntity.getEndTime())
                    .careTargetName(contractEntity.getJobOffer().getCareTarget().getName())
                    .storeName(images.get(0).getStoreFileName())
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

        careNoteEntity.start(attachFile);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> getSchedule(Long id) {

        Optional<CareNote> careNote = careNoteRepository.findById(id);
        CareNote careNoteEntity = careNote.orElse(null);
        CareTargetSchedule careTargetSchedule = careTargetScheduleRepository.findByCareNoteId(id);

        List<ActivityInformation> activityInformations = activityInformationRepository.findAllByCareTargetScheduleId(careTargetSchedule.getId());
        List<ActivityInformationDTO> activityInformationDTOs = new ArrayList<>();

        for (ActivityInformation activityInformation : activityInformations) {
            ActivityInformationDTO activityInformationDTO = ActivityInformationDTO.builder()
                    .id(activityInformation.getId())
                    .name(activityInformation.getActivityClassification().getName())
                    .startTime(activityInformation.getStartTime())
                    .endTime(activityInformation.getEndTime())
                    .requirement(activityInformation.getRequirement())
                    .build();

            if(activityInformation.getContent() != null) {
                activityInformationDTO.setContent(activityInformation.getContent());
            }
            if(activityInformation.getStoreFileName() != null) {
                activityInformationDTO.setStoreFileName(activityInformation.getStoreFileName());
            }

            activityInformationDTOs.add(activityInformationDTO);
        }

        CareNoteScheduleDTO careNoteScheduleDTO = CareNoteScheduleDTO.builder()
                .id(careNoteEntity.getId())
                .date(careNoteEntity.getDate())
                .startTime(careTargetSchedule.getStartTime())
                .endTime(careTargetSchedule.getEndTime())
                .activityInformationDTOs(activityInformationDTOs)
                .build();

        MyResponse<CareNoteScheduleDTO> body = MyResponse.<CareNoteScheduleDTO>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(careNoteScheduleDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);    }

    @Override
    public ResponseEntity<MyResponse> saveContent(Long id, String content) {

        Optional<ActivityInformation> activityInformation = activityInformationRepository.findById(id);
        ActivityInformation activityInformationEntity = activityInformation.orElse(null);
        activityInformationEntity.postContent(content);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> savePhoto(Long id, CareNoteImageDTO imageDTO) throws IOException {

        Optional<ActivityInformation> activityInformation = activityInformationRepository.findById(id);
        ActivityInformation activityInformationEntity = activityInformation.orElse(null);

        UploadFile attachFile = fileStoreService.storeFile(imageDTO.getAttachFile());
        activityInformationEntity.savePhoto(attachFile);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> removeContent(Long id) {

        Optional<ActivityInformation> activityInformation = activityInformationRepository.findById(id);
        ActivityInformation activityInformationEntity = activityInformation.orElse(null);

        activityInformationEntity.removeContent();

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> removePhoto(Long id) {

        Optional<ActivityInformation> activityInformation = activityInformationRepository.findById(id);
        ActivityInformation activityInformationEntity = activityInformation.orElse(null);

        activityInformationEntity.removePhoto();

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> complete(Long id) {

        Optional<CareNote> careNote = careNoteRepository.findById(id);
        CareNote careNoteEntity = careNote.orElse(null);

        careNoteEntity.complete();

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> getContracts(PrincipalDetails principalDetails) {
        CareSitter careSitter = principalDetails.getCareSitter();

        List<Contract> contracts = contractRepository.findByCareSitterAndStatus(careSitter, 2);

        List<CareSitterCompletionContractDTO> careSitterCompletionContractDTOs = new ArrayList<>();

        for (Contract contract : contracts) {

            JobOffer jobOffer = contract.getJobOffer();
            CareTarget careTarget = jobOffer.getCareTarget();
            List<CareTargetImage> images = careTargetImageRepository.findAllByCareTarget(careTarget);


            CareSitterCompletionContractDTO dto = CareSitterCompletionContractDTO.builder()
                    .id(contract.getId())
                    .startDate(contract.getStartDate())
                    .endDate(contract.getEndDate())
                    .amount(contract.getAmount())
                    .payDateTime(contract.getPayDateTime())
                    .jobOfferId(jobOffer.getId())
                    .day(jobOffer.getDay())
                    .memberId(contract.getMember().getId())
                    .memberName(contract.getMember().getName())
                    .careTargetId(careTarget.getId())
                    .careTargetName(careTarget.getName())
                    .careTargetImage(images.get(0).getStoreFileName())
                    .gender(String.valueOf(careTarget.getGender()))
                    .birth(careTarget.getBirth())
                    .zipcode(careTarget.getZipcode())
                    .address(careTarget.getAddress())
                    .detailedAddress(careTarget.getDetailedAddress())
                    .pet(careTarget.getPet())
                    .isCctvAgreement(careTarget.getIsCctvAgreement())
                    .careType(careTarget.getCareType())
                    .coronaTest(careTarget.getCoronaTest())
                    .build();

            careSitterCompletionContractDTOs.add(dto);
        }

        MyResponse<List<CareSitterCompletionContractDTO>> body = MyResponse.<List<CareSitterCompletionContractDTO>>builder()
                .header(StatusEnum.OK)
                .body(careSitterCompletionContractDTOs)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> getCareNotes(Long id) {

        Optional<Contract> contract = contractRepository.findById(id);
        Contract contractEntity = contract.orElse(null);
        CareSitter careSitter = contractEntity.getCareSitter();

        List<CareNote> careNotes = careNoteQueryRepository.findAllByCareSitterAndContractId(careSitter, id);

        List<CareSitterCompletionCareNoteDTO> careSitterCompletionCareNoteDTOs = new ArrayList<>();

        for (CareNote careNote : careNotes) {
            CareSitterCompletionCareNoteDTO dto = CareSitterCompletionCareNoteDTO.builder()
                    .id(careNote.getId())
                    .date(careNote.getDate())
                    .startTime(careNote.getStartTime())
                    .endTime(careNote.getEndTime())
                    .storeFileName(careNote.getStoreFileName())
                    .build();

            careSitterCompletionCareNoteDTOs.add(dto);
        }

        MyResponse<List<CareSitterCompletionCareNoteDTO>> body = MyResponse.<List<CareSitterCompletionCareNoteDTO>>builder()
                .header(StatusEnum.OK)
                .body(careSitterCompletionCareNoteDTOs)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> getCareNote(Long id) {
        return null;
    }
}
