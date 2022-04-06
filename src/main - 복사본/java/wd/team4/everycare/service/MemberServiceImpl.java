package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.member.MemberDTO;
import wd.team4.everycare.dto.member.SignupDTO;
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

    public MyResponse<SignupDTO> join(SignupDTO signupDTO) {
        // 1. memberRepository에 해당 아이디 가입되어있는지 확인
        isEmpty(signupDTO.getId());

        // 2. 엔티티로 변환 후 가입
        save(signupDTO);

        // 3. MyResponse 타입으로 만들어서 DTO 반환
        return MyResponse.<SignupDTO>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(signupDTO)
                .build();
    }

    @Override
    public Optional<Member> isPresent(String id){
        Optional<Member> member = memberRepository.findById(id);
        if(member.isPresent()){
            return member;
        } else{
            throw new MemberNotFoundException();
        }
    }

    @Override
    public boolean isEmpty(String id){
        if(memberRepository.findById(id).isEmpty()) {
            return true;
        } throw new MemberHasExistException(id);
    }


    @Override
    public String save(SignupDTO signupDTO) {
        signupDTO.encodePassword(bCryptPasswordEncoder.encode(signupDTO.getPassword()));
        Member member = signupDtoToEntity(signupDTO);
        memberRepository.save(member);
        return member.getId();
    }

    @Override
    public Member signupDtoToEntity(SignupDTO signupDTO){
        return MemberService.super.signupDtoToEntity(signupDTO);
    }


}
