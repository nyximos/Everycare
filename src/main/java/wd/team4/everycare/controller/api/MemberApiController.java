package wd.team4.everycare.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.config.jwt.JwtProperties;
import wd.team4.everycare.domain.ActivityStatus;
import wd.team4.everycare.domain.MemberRole;
import wd.team4.everycare.dto.member.MemberAccountDTO;
import wd.team4.everycare.dto.member.PasswordDTO;
import wd.team4.everycare.dto.member.SignupDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.service.MemberServiceImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
// @CrossOrigin(origins = "http://localhost:8087"*/32 m)  // CORS 허용
public class MemberApiController {

    private final MemberServiceImpl memberService;

    // 1. 로그인 안한 유저로 저장
    // 2. 로그인 후 정상응답이 되면 서버로 api 요청을 보냄
    // 3. 로그인 했으므로 해당 권한을 보내줌
    // 4. 권한이 있는 유저로 저장

    // 쿠키에 저장된 토큰 삭제
    @DeleteMapping("/token")
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie(JwtProperties.HEADER_STRING, null);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        String header = response.getHeader(HttpHeaders.SET_COOKIE);
        response.setHeader(HttpHeaders.SET_COOKIE, String.format("%s; Secure; %s", header, "SameSite=None"));
        MyResponse<Object> message = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("로그아웃 성공")
                .build();
        return new ResponseEntity(message, HttpStatus.OK);
    }

    // 유저 혹은 매니저 혹은 어드민이 접근 가능
    @GetMapping("/user")
    public ResponseEntity<MyResponse> user(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        String username = principalDetails.getUsername();
        ResponseEntity<MyResponse> responseEntity = memberService.getMemberInfoDTO(username);

        return responseEntity;
    }

    @PostMapping("/signup")
    public String join(@Valid @ModelAttribute SignupDTO signupDTO) {

        LocalDateTime time = LocalDateTime.now();
        signupDTO.setCreatedAt(time);
        signupDTO.setActivityStatus(ActivityStatus.ACTIVE);
        signupDTO.setRole(MemberRole.ROLE_MEMBER);


        memberService.join(signupDTO);
        return "회원가입완료";
    }

    @GetMapping("/dashboard/account")
    public ResponseEntity<MyResponse> getAccount(@AuthenticationPrincipal PrincipalDetails principalDetails){
        ResponseEntity<MyResponse> responseEntity = memberService.getAccountDTO(principalDetails);
        return responseEntity;
    }

    @PatchMapping("/dashboard/account")
    public ResponseEntity<MyResponse> updateAccount(@AuthenticationPrincipal PrincipalDetails principalDetails, @ModelAttribute MemberAccountDTO memberAccountDTO) {
        ResponseEntity<MyResponse> responseEntity = memberService.updateAccount(principalDetails, memberAccountDTO);
        return responseEntity;
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<MyResponse> redundantCheck(@PathVariable("id") String id) {
        ResponseEntity<MyResponse> responseEntity = memberService.redundantCheck(id);
        return responseEntity;
    }

    @PatchMapping("/member/{id}/password")
    public ResponseEntity<MyResponse> changePassword(@PathVariable("id") String id, @ModelAttribute PasswordDTO passwordDTO) {
        ResponseEntity<MyResponse> responseEntity = memberService.changePassword(id, passwordDTO);
        return responseEntity;
    }

    @GetMapping("/teeeeest")
    public ResponseEntity<MyResponse> getMyJobOffer(@AuthenticationPrincipal PrincipalDetails principalDetails){
        ResponseEntity<MyResponse> myJobOffer = memberService.getMyJobOffer(principalDetails);
        return myJobOffer;
    }

}




