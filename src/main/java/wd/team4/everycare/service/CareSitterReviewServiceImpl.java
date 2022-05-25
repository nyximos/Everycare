package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.ActivityClassification;
import wd.team4.everycare.domain.ActivityInformation;
import wd.team4.everycare.domain.CareTargetSchedule;
import wd.team4.everycare.dto.careSitterReview.CareSitterReviewCategoryDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.CareNoteRepository;
import wd.team4.everycare.repository.CareSitterReviewQueryRepository;
import wd.team4.everycare.repository.CareSitterReviewRepository;
import wd.team4.everycare.repository.CareTargetScheduleRepository;
import wd.team4.everycare.service.interfaces.CareSitterReviewService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CareSitterReviewServiceImpl implements CareSitterReviewService {

    private final CareSitterReviewRepository careSitterReviewRepository;
    private final CareTargetScheduleRepository careTargetScheduleRepository;
    private final CareSitterReviewQueryRepository careSitterReviewQueryRepository;


    @Override
    public ResponseEntity<MyResponse> getAllCategory(Long id) {

        CareTargetSchedule careNote = careTargetScheduleRepository.findByCareNoteId(id);
        List<ActivityClassification> activityClassifications = careSitterReviewQueryRepository.findAllCategory(careNote.getId());

        List<CareSitterReviewCategoryDTO> careSitterReviewCategoryDTOs = new ArrayList<>();

        for (ActivityClassification activityClassification : activityClassifications) {

            CareSitterReviewCategoryDTO dto = CareSitterReviewCategoryDTO.builder()
                    .id(activityClassification.getId())
                    .name(activityClassification.getName())
                    .build();

            careSitterReviewCategoryDTOs.add(dto);
        }

        MyResponse<List<CareSitterReviewCategoryDTO>> body = MyResponse.<List<CareSitterReviewCategoryDTO>>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(careSitterReviewCategoryDTOs)
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}
