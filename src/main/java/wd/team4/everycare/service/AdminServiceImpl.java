package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.service.interfaces.AdminService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    private final MemberRepository memberRepository;

    @Override
    public ResponseEntity<MyResponse> registration(String id, LocalDateTime time) {

        Optional<Member> member = memberRepository.findById(id);
        Member memberEntity = member.orElse(null);
        System.out.println("memberEntity = " + memberEntity);
        member.get().registrationAdmin(time);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공했슴다~")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}
