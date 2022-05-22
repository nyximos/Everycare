package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.domain.MemberSchedule;
import wd.team4.everycare.dto.member.MemberScheduleDetailDTO;
import wd.team4.everycare.dto.member.MemberScheduleFormDTO;
import wd.team4.everycare.dto.member.MemberScheduleListDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.MemberScheduleRepository;
import wd.team4.everycare.service.interfaces.MemberScheduleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberScheduleServiceImpl implements MemberScheduleService {

    private final MemberScheduleRepository memberScheduleRepository;


    @Override
    public ResponseEntity<MyResponse> getAll(PrincipalDetails principalDetails) {

        Member user = principalDetails.getUser();

        List<MemberSchedule> memberSchedules = memberScheduleRepository.findAllByMember(user);
        List<MemberScheduleListDTO> memberScheduleListDTOs = new ArrayList<>();

        if (memberSchedules.isEmpty()) {
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("작성된 일정이 없습니다.")
                    .build();

            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        }

        for (MemberSchedule memberSchedule : memberSchedules) {
            MemberScheduleListDTO dto = MemberScheduleListDTO.builder()
                    .id(memberSchedule.getId())
                    .name(memberSchedule.getName())
                    .startDatetime(memberSchedule.getStartDatetime())
                    .endDatetime(memberSchedule.getEndDatetime())
                    .build();

            memberScheduleListDTOs.add(dto);
        }


        MyResponse<List<MemberScheduleListDTO>> body = MyResponse.<List<MemberScheduleListDTO>>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(memberScheduleListDTOs)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> get(Long id) {

        Optional<MemberSchedule> memberSchedule = memberScheduleRepository.findById(id);
        MemberSchedule memberScheduleEntity = memberSchedule.orElse(null);

        MemberScheduleDetailDTO memberScheduleDetailDTO = MemberScheduleDetailDTO.builder()
                .id(memberScheduleEntity.getId())
                .name(memberScheduleEntity.getName())
                .startDatetime(memberScheduleEntity.getStartDatetime())
                .endDatetime(memberScheduleEntity.getEndDatetime())
                .alarmTime(memberScheduleEntity.getAlarmTime())
                .addressName(memberScheduleEntity.getAddressName())
                .build();

        MyResponse<MemberScheduleDetailDTO> body = MyResponse.<MemberScheduleDetailDTO>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(memberScheduleDetailDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> save(PrincipalDetails principalDetails, MemberScheduleFormDTO memberScheduleFormDTO) {

        Member user = principalDetails.getUser();

        MemberSchedule memberSchedule = MemberSchedule.builder()
                .name(memberScheduleFormDTO.getName())
                .startDatetime(memberScheduleFormDTO.getStartDatetime())
                .endDatetime(memberScheduleFormDTO.getEndDatetime())
                .member(user)
                .build();

        if (memberScheduleFormDTO.getAlarmTime() != null) {
            memberSchedule.saveAlarmTime(memberScheduleFormDTO.getAlarmTime());
        }

        if (memberSchedule.getAddressName() != null) {
            memberSchedule.saveAddressName(memberScheduleFormDTO.getAddressName());
        }

        if(memberSchedule.getComment() != null) {
            memberSchedule.saveComment(memberScheduleFormDTO.getComment());
        }

        memberScheduleRepository.save(memberSchedule);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> update(Long id, MemberScheduleFormDTO memberScheduleFormDTO) {

        Optional<MemberSchedule> memberSchedule = memberScheduleRepository.findById(id);
        MemberSchedule memberScheduleEntity = memberSchedule.orElse(null);

        memberScheduleEntity.update(memberScheduleFormDTO);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> remove(Long id) {

        memberScheduleRepository.deleteById(id);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}
