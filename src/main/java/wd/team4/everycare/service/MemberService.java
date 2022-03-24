package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.member.MemberDTO;
import wd.team4.everycare.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(MemberDTO memberDTO) {
        // 1. db 조회 후 중복회원인지 확인

        // 2. 엔티티로 변환 후 가입

        // 3. MyResponse 타입으로 만들어서 DTO 반환


    }
}
