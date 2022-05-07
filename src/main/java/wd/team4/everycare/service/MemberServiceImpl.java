package wd.team4.everycare.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.config.jwt.JwtProperties;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.careSitter.CareSitterDTO;
import wd.team4.everycare.dto.member.MemberAccountDTO;
import wd.team4.everycare.dto.member.MemberInfoDTO;
import wd.team4.everycare.dto.member.SignupDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.service.exception.MemberHasExistException;
import wd.team4.everycare.service.exception.MemberNotFoundException;
import wd.team4.everycare.service.interfaces.MemberService;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    private final PrincipalDetails principalDetails;

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

    @Override
    public ResponseEntity<MyResponse> getMemberInfoDTO(String id) {

        Optional<Member> member = memberRepository.findById(id);
        Member memberEntity = member.orElse(null);
        MemberInfoDTO memberInfoDTO = memberEntity.toMemberInfoDTO();

        MyResponse<MemberInfoDTO> body = MyResponse.<MemberInfoDTO>builder()
                .header(StatusEnum.OK)
                .message("성공했슴다~")
                .body(memberInfoDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> getAccountDTO(PrincipalDetails principalDetails) {
        String username = principalDetails.getUsername();
        Optional<Member> member = memberRepository.findById(username);
        Member memberEntity = member.orElse(null);

        MemberAccountDTO accountDTO = MemberAccountDTO.builder()
                .id(memberEntity.getId())
                .name(memberEntity.getName())
                .gender(memberEntity.getGender())
                .birth(memberEntity.getBirth())
                .phone(memberEntity.getPhone())
                .email(memberEntity.getEmail())
                .zipcode(memberEntity.getZipcode())
                .address(memberEntity.getAddress())
                .detailedAddress(memberEntity.getDetailedAddress())
                .bank(memberEntity.getBank())
                .accountNumber(memberEntity.getAccountNumber())
                .build();

        MyResponse<MemberAccountDTO> body = MyResponse.<MemberAccountDTO>builder()
                .header(StatusEnum.OK)
                .message("성공했슴다~")
                .body(accountDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> updateAccount(PrincipalDetails principalDetails, MemberAccountDTO memberAccountDTO) {
        String username = principalDetails.getUsername();
        Optional<Member> member = memberRepository.findById(username);
        Member memberEntity = member.orElse(null);
        memberEntity.updateAccount(memberAccountDTO);

        MyResponse<MemberAccountDTO> body = MyResponse.<MemberAccountDTO>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(memberAccountDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);



    }

//    @Override
//    public String getId(String authorization) {
//        return principalDetails.getUsername();
//    }

    public void getUser(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization");
//        String token = accessToken.replace(JwtProperties.TOKEN_PREFIX, "");
        Claims body = Jwts.parser().setSigningKey(JwtProperties.SECRET.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(accessToken).getBody();

        System.out.println(body.getSubject());
        System.out.println(body.getExpiration());
        System.out.println(body.get("username"));
    }

}
