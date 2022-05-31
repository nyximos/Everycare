package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.*;
import wd.team4.everycare.dto.careNote.CareSitterCareNoteReviewDTO;
import wd.team4.everycare.dto.careNote.CareSitterCompletionCareNoteDTO;
import wd.team4.everycare.dto.careNote.CareSitterCompletionCareNoteDetailDTO;
import wd.team4.everycare.dto.careTargetSchedule.CareNoteActivityInformationDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.*;
import wd.team4.everycare.repository.query.CareNoteQueryRepository;
import wd.team4.everycare.service.interfaces.CareNoteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CareNoteServiceImpl implements CareNoteService {

    private final CareNoteRepository careNoteRepository;
    private final ContractRepository contractRepository;
    private final CareNoteQueryRepository careNoteQueryRepository;
    private final CareSitterReviewRepository careSitterReviewRepository;
    private final CareTargetScheduleRepository careTargetScheduleRepository;
    private final ActivityInformationRepository activityInformationRepository;


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

        Optional<CareNote> careNote = careNoteRepository.findById(id);
        CareNote careNoteEntity = careNote.orElse(null);

        CareTargetSchedule careTargetSchedule = careTargetScheduleRepository.findByCareNoteId(id);

        List<ActivityInformation> activityInformations = activityInformationRepository.findAllByCareTargetSchedule(careTargetSchedule);
        List<CareSitterCareNoteReviewDTO> reviewDTOs = new ArrayList<>();
        List<CareNoteActivityInformationDTO> informationDTOs = new ArrayList<>();

        for (ActivityInformation activityInformation : activityInformations) {
            CareNoteActivityInformationDTO informationDTO = CareNoteActivityInformationDTO.builder()
                    .id(activityInformation.getId())
                    .name(activityInformation.getActivityClassification().getName())
                    .startTime(activityInformation.getStartTime())
                    .endTime(activityInformation.getEndTime())
                    .requirement(activityInformation.getRequirement())
                    .build();
            informationDTOs.add(informationDTO);
        }

        List<CareSitterReview> reviews = careSitterReviewRepository.findAllByCareTargetSchedule(careTargetSchedule);
        for (CareSitterReview review : reviews) {
            CareSitterCareNoteReviewDTO reviewDTO = CareSitterCareNoteReviewDTO.builder()
                    .id(review.getId())
                    .rating(review.getRating())
                    .comment(review.getComment())
                    .updatedAt(review.getUpdatedAt())
                    .categoryName(review.getActivityClassification().getName())
                    .build();
            reviewDTOs.add(reviewDTO);
        }

        CareSitterCompletionCareNoteDetailDTO careNoteDetailDTO = CareSitterCompletionCareNoteDetailDTO.builder()
                .id(careNoteEntity.getId())
                .date(careNoteEntity.getDate())
                .startTime(careNoteEntity.getStartTime())
                .endTime(careNoteEntity.getEndTime())
                .storeFileName(careNoteEntity.getStoreFileName())
                .careNoteActivityInformationDTOs(informationDTOs)
                .careSitterCareNoteReviewDTOs(reviewDTOs)
                .build();

        MyResponse<CareSitterCompletionCareNoteDetailDTO> body = MyResponse.<CareSitterCompletionCareNoteDetailDTO>builder()
                .header(StatusEnum.OK)
                .body(careNoteDetailDTO)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}
