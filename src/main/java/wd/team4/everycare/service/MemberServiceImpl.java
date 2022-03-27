package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.member.MemberDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.service.exception.MemberHasExistException;
import wd.team4.everycare.service.exception.MemberNotFoundException;
import wd.team4.everycare.service.interfaces.MemberService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MyResponse<MemberDTO> join(MemberDTO memberDTO) {
        // 1. memberRepository에 해당 아이디 가입되어있는지 확인
        isEmpty(memberDTO.getId());

        // 2. 엔티티로 변환 후 가입
        save(memberDTO);

        // 3. MyResponse 타입으로 만들어서 DTO 반환
        return MyResponse.<MemberDTO>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(memberDTO)
                .build();
    }

    public Optional<Member> isPresent(String id){
        Optional<Member> member = memberRepository.findById(id);
        if(member.isPresent()){
            return member;
        } else{
            throw new MemberNotFoundException();
        }
    }

    public boolean isEmpty(String id){
        if(memberRepository.findById(id).isEmpty()) {
            return true;
        } throw new MemberHasExistException(id);
    }


    @Override
    public String save(MemberDTO dto) {
        dto.encodePassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        Member member = dtoToEntity(dto);
        memberRepository.save(member);
        return member.getId();
    }

    @Override
    public Member dtoToEntity(MemberDTO dto) {
        return Member.builder()
                .id(dto.getId())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
    }

}
