package wd.team4.everycare.service;

import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.ActivityClassification;
import wd.team4.everycare.domain.Bedge;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.CareSitterBedge;
import wd.team4.everycare.dto.badge.CareSitterBadgeDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.ActivityClassificationRepository;
import wd.team4.everycare.repository.BadgeRepository;
import wd.team4.everycare.repository.CareSitterBadgeRepository;
import wd.team4.everycare.repository.query.CareSitterReviewQueryRepository;
import wd.team4.everycare.service.interfaces.BadgeService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BadgeServiceImpl implements BadgeService {

    private final BadgeRepository badgeRepository;
    private final CareSitterBadgeRepository careSitterBadgeRepository;
    private final CareSitterReviewQueryRepository careSitterReviewQueryRepository;
    private final ActivityClassificationRepository activityClassificationRepository;


    public ResponseEntity<MyResponse> giveBadge(PrincipalDetails principalDetails) {
        CareSitter careSitter = principalDetails.getUser().getCareSitter();
        List<Tuple> activityClassification = careSitterReviewQueryRepository.findActivityClassification(careSitter);

        for (Tuple tuple : activityClassification) {
            Double avg = tuple.get(1, Double.class);
            Long count = tuple.get(2, Long.class);
            System.out.println("avg = " + avg);
            System.out.println("count = " + count);

            ActivityClassification ac = tuple.get(0, ActivityClassification.class);
            Bedge bedge = badgeRepository.findByActivityClassification(ac).orElse(null);

            careSitterBadgeRepository.findByBedge(bedge);
            if (avg > 4.5 && count > 10) {

                CareSitterBedge careSitterBadge = CareSitterBedge.builder()
                        .careSitter(careSitter)
                        .bedge(bedge)
                        .build();

                CareSitterBedge careSitterBedge = careSitterBadgeRepository.findByBedge(bedge).orElse(null);

                if (careSitterBedge==null){
                    careSitterBadgeRepository.save(careSitterBadge);
                }
            }else if(avg<4.5 && count<10){
                CareSitterBedge careSitterBedge = careSitterBadgeRepository.findByBedge(bedge).orElse(null);
                careSitterBadgeRepository.deleteById(careSitterBedge.getId());
            }
        }
        MyResponse body = MyResponse.builder()
                .message("뱃지 지급")
                .header(StatusEnum.OK)
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> findBadge(PrincipalDetails principalDetails) {
        CareSitter careSitter = principalDetails.getUser().getCareSitter();

        List<CareSitterBedge> careSitterBadgeList = careSitterBadgeRepository.findByCareSitter(careSitter);

        List<CareSitterBadgeDTO> careSitterBadgeDTOs = new ArrayList<>();

        careSitterBadgeList.stream().map(careSitterBadge -> careSitterBadge.toCareSitterBadgeDTO()).forEach(careSitterBadgeDTOs::add);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("보유 뱃지 조회")
                .body(careSitterBadgeDTOs)
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}
