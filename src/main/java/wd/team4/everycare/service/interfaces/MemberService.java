package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.member.MemberAccountDTO;
import wd.team4.everycare.dto.member.SignupDTO;
import wd.team4.everycare.dto.response.MyResponse;

import java.util.Optional;

public interface MemberService {

    ResponseEntity<MyResponse> getAll();

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

    ResponseEntity<MyResponse> getMemberInfoDTO(String username);

    ResponseEntity<MyResponse> getAccountDTO(PrincipalDetails principalDetails);

    ResponseEntity<MyResponse> updateAccount(PrincipalDetails principalDetails, MemberAccountDTO memberAccountDTO);

    ResponseEntity<MyResponse> getMyJobOffer(PrincipalDetails principalDetails);

    ResponseEntity<MyResponse> redundantCheck(String id);

//    String getId(String authorization);

}
