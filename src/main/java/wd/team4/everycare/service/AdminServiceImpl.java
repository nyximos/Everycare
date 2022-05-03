package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.Certification;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.domain.Store;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.CertificationRepository;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.repository.StoreRepository;
import wd.team4.everycare.service.interfaces.AdminService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final CertificationRepository certificationRepository;

    @Override
    public ResponseEntity<MyResponse> registrationAdmin(String id, LocalDateTime time) {

        Optional<Member> member = memberRepository.findById(id);
        Member memberEntity = member.orElse(null);
        System.out.println("memberEntity = " + memberEntity);
        if (memberEntity == null) {
            MyResponse<Object> body = MyResponse.builder()
                    .header(StatusEnum.BAD_REQUEST)
                    .message("멤버가 존재하지 않습니다.").build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.BAD_REQUEST);
        } else {
            memberEntity.registrationAdmin(time);
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("성공했슴다~")
                    .build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);

        }
    }

    @Override
    public ResponseEntity<MyResponse> approveStore(Long id) {
        LocalDateTime time = LocalDateTime.now();
        Optional<Store> store = storeRepository.findById(id);
        Store storeEntity = store.orElse(null);
        if ( storeEntity == null ) {
            MyResponse<Object> body = MyResponse.builder()
                    .header(StatusEnum.BAD_REQUEST)
                    .message("스토어가 존재하지 않습니다.").build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.BAD_REQUEST);
        } else {
            storeEntity.approvedByAdmin(1, time);

            MyResponse body= MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("성공")
                    .build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<MyResponse> approveCertification(Long id, Member admin) {
        LocalDateTime time = LocalDateTime.now();
        Optional<Certification> certification = certificationRepository.findById(id);
        Certification certificationEntity = certification.orElse(null);
        if (certificationEntity == null){
            MyResponse<Object> body = MyResponse.builder()
                    .header(StatusEnum.BAD_REQUEST)
                    .message("자격증이 존재하지 않습니다.").build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.BAD_REQUEST);
        } else {
            certificationEntity.approvedByAdmin(1,time,admin);

            MyResponse body= MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("성공")
                    .build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        }
    }
}
