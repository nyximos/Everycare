package wd.team4.everycare.service.interfaces;

import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.member.MemberDTO;
import wd.team4.everycare.dto.member.SignupDTO;

import java.util.Optional;

public interface MemberService {

    String save(SignupDTO dto);

    Optional<Member> isPresent(String id);

    boolean isEmpty(String id);

    default Member signupDtoToEntity(SignupDTO signupDTO){
        Member member = Member.builder()
                .id(signupDTO.getId())
                .password(signupDTO.getPassword())
                .name(signupDTO.getName())
                .role(signupDTO.getRole())
                .gender(signupDTO.getGender())
                .birth(signupDTO.getBirth())
                .phone(signupDTO.getPhone())
                .email(signupDTO.getEmail())
                .createdAt(signupDTO.getCreatedAt())
                .activityStatus(signupDTO.getActivityStatus())
                .zipcode(signupDTO.getZipcode())
                .address(signupDTO.getAddress())
                .detailedAddress(signupDTO.getDetailedAddress())
                .bank(signupDTO.getBank())
                .accountNumber(signupDTO.getAccountNumber())
                .build();
        return member;
    }

//    String getId(String authorization);

}
