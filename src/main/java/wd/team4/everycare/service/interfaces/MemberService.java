package wd.team4.everycare.service.interfaces;

import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.member.MemberDTO;

public interface MemberService {

    String save(MemberDTO dto);

    default Member dtoToEntity(MemberDTO dto){
        Member member = Member.builder()
                .id(dto.getId())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
        return member;

    }
}
