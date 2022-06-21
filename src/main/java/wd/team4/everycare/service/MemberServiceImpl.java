package wd.team4.everycare.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.config.jwt.JwtProperties;
import wd.team4.everycare.domain.*;
import wd.team4.everycare.dto.jobOffer_jobSearch.JobOfferListDTO;
import wd.team4.everycare.dto.member.*;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.CareTargetImageRepository;
import wd.team4.everycare.repository.JobOfferRepository;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.service.exception.MemberHasExistException;
import wd.team4.everycare.service.exception.MemberNotFoundException;
import wd.team4.everycare.service.interfaces.MemberService;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JobOfferRepository jobOfferRepository;
    private final CareTargetImageRepository careTargetImageRepository;
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
    public Optional<Member> isPresent(String id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            return member;
        } else {
            throw new MemberNotFoundException();
        }
    }

    @Override
    public boolean isEmpty(String id) {
        if (memberRepository.findById(id).isEmpty()) {
            return true;
        }
        throw new MemberHasExistException(id);
    }


    @Override
    public ResponseEntity<MyResponse> getAll() {
        List<Member> members = memberRepository.findAll();
        List<MemberListViewDTO> memberListViewDTOs = new ArrayList<>();

        for (Member member : members) {
            MemberListViewDTO memberListViewDTO = MemberListViewDTO.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .role(member.getRole())
                    .gender(member.getGender())
                    .birth(member.getBirth())
                    .phone(member.getPhone())
                    .email(member.getEmail())
                    .createdAt(member.getCreatedAt())
                    .activityStatus(member.getActivityStatus())
                    .build();
            memberListViewDTOs.add(memberListViewDTO);
        }

        MyResponse<List<MemberListViewDTO>> body = MyResponse.<List<MemberListViewDTO>>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(memberListViewDTOs)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public String save(SignupDTO signupDTO) {
        signupDTO.encodePassword(bCryptPasswordEncoder.encode(signupDTO.getPassword()));
        Member member = signupDtoToEntity(signupDTO);
        memberRepository.save(member);
        return member.getId();
    }

    @Override
    public Member signupDtoToEntity(SignupDTO signupDTO) {
        return MemberService.super.signupDtoToEntity(signupDTO);
    }

    @Override
    public ResponseEntity<MyResponse> getMemberInfoDTO(String id) {

        Optional<Member> member = memberRepository.findById(id);
        Member memberEntity = member.orElse(null);

        CareSitter careSitter = memberEntity.getCareSitter();
        Store store = memberEntity.getStore();

        MemberInfoDTO memberInfoDTO = MemberInfoDTO.builder()
                .id(memberEntity.getId())
                .name(memberEntity.getName())
                .role(memberEntity.getRole())
                .build();

        if (careSitter != null) {
            memberInfoDTO.setCareSitterId(memberEntity.getCareSitter().getId());
        }
        if (store != null) {
            memberInfoDTO.setStoreId(memberEntity.getStore().getId());
        }

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

    @Override
    public ResponseEntity<MyResponse> getMyJobOffer(PrincipalDetails principalDetails) {
        Member user = principalDetails.getUser();
        List<JobOffer> jobOfferList = jobOfferRepository.findByMember(user);
        List<JobOfferListDTO> jobOfferDTOs = new ArrayList<>();

        jobOfferList.stream().map(jobOffer -> jobOffer.toJobOfferListDTO(jobOffer)).forEach(jobOfferDTOs::add);
        for (JobOfferListDTO jobOfferDTO : jobOfferDTOs) {
            Long careTargetId = jobOfferDTO.getCareTarget().getId();
            List<CareTargetImage> careTargetImages = careTargetImageRepository.findAllByCareTargetId(careTargetId);
            String storeFileName = careTargetImages.get(0).getStoreFileName();
            jobOfferDTO.setCareTargetImage(storeFileName);
        }

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("등록한 구인글 조회")
                .body(jobOfferDTOs)
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> redundantCheck(String id) {

        Optional<Member> member = memberRepository.findById(id);

        if (member.isEmpty()) {
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("성공")
                    .body(id)
                    .build();

            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        } else {
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("실패")
                    .body(null)
                    .build();

            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<MyResponse> changePassword(String id, PasswordDTO passwordDTO) {

        Optional<Member> member = memberRepository.findById(id);
        Member memberEntity = member.orElse(null);


        if (bCryptPasswordEncoder.matches(passwordDTO.getPassword(), memberEntity.getPassword())) {

            String password = bCryptPasswordEncoder.encode(passwordDTO.getNewPassword());
            memberEntity.updatePassword(password);

            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("변경 성공")
                    .build();

            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);


        } else {

            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.BAD_REQUEST)
                    .message("변경 실패")
                    .build();

            return new ResponseEntity<MyResponse>(body, HttpStatus.BAD_REQUEST);
        }
    }
}

