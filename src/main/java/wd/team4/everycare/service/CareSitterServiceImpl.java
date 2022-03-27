package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.CareSitterDTO;
import wd.team4.everycare.repository.CareSitterRepository;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.service.interfaces.CareSitterService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CareSitterServiceImpl implements CareSitterService {

    private final CareSitterRepository careSitterRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long save(CareSitterDTO dto) {
        CareSitter careSitter = dtoToEntity(dto);
        careSitterRepository.save(careSitter);
        return careSitter.getId();
    }

    @Override
    public CareSitter dtoToEntity(CareSitterDTO dto) {
        return CareSitter.builder()
                .preferredType(dto.getPreferredType())
                .desiredDayWeek(dto.getDesiredHourlyWage())
                .activityTime(dto.getActivityTime())
                .desiredHourlyWage(dto.getDesiredHourlyWage())
                .desiredMonthlyWage(dto.getDesiredMonthlyWage())
                .cctvAgreement(dto.getCctvAgreement())
                .vaccination(dto.getVaccination())
                .introduction(dto.getIntroduction())
                .disclosureStatus(1)
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }

    @Override
    public void removeCareSitter(Long id) {
        careSitterRepository.deleteById(id);
    }

    @Override
    public String isPresent(String id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.get().getId();
    }

    @Override
    public boolean isEmpty(String id) {
        if(isEmpty(String.valueOf(memberRepository.findById(id)))) {
            return true;
        }
        return false;
    }

}
