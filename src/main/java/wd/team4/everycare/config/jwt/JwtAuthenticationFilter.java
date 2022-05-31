package wd.team4.everycare.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.ActivityStatus;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.LoginRequestDTO;
import wd.team4.everycare.repository.MemberRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Optional;

/*
 스프링 시큐리티에서 UsernamePasswordAuthenticationFilter 가 있음
 /login 요청해서 username,password 전송 (post)
 UsernamePasswordAuthenticationFilter 동작 함
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private final AuthenticationManager authenticationManager;

    // Authentication 객체 만들어서 리턴 => 의존 : AuthenticationManager
    // 인증 요청시에 실행되는 함수 => /login
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        System.out.println("JwtAuthenticationFilter : 로그인 시도");
        // request에 있는 username과 password (json)를 파싱해서 자바 Object로 받기
        ObjectMapper om = new ObjectMapper();
        LoginRequestDTO loginRequestDTO = null;
        try {
            loginRequestDTO = om.readValue(request.getInputStream(), LoginRequestDTO.class);
        } catch (NullPointerException e ) {
            System.out.println("e.getMessage() = " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("JwtAuthenticationFilter : "+loginRequestDTO);

        // 유저네임패스워드 토큰 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword());

        System.out.println("JwtAuthenticationFilter : 토큰생성완료");

        // authenticate() 함수가 호출 되면 인증 프로바이더가
        // UserDetailsService의 loadUserByUsername(토큰의 첫번째 파라메터) 를 호출하고
        // UserDetails를 리턴받아서 토큰의 두번째 파라메터(credential)과
        // UserDetails(DB값)의 getPassword()함수로 비교해서 동일하면
        // Authentication 객체를 만들어서 필터체인으로 리턴해준다.
        // 만들면 로그인이 되었다는 뜻임

        // Tip: 인증 프로바이더의 디폴트 서비스는 UserDetailsService 타입
        // Tip: 인증 프로바이더의 디폴트 암호화 방식은 BCryptPasswordEncoder
        // 결론은 인증 프로바이더에게 알려줄 필요가 없음.
        Authentication authentication =
                authenticationManager.authenticate(authenticationToken);

        PrincipalDetails principalDetailis = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("Authentication : "+principalDetailis.getUser().getId());
        return authentication;
    }

    // attemptAuthentication 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수가 실행됨.
    // JWT Token 생성해서 response에 담아주기
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        PrincipalDetails principalDetailis = (PrincipalDetails) authResult.getPrincipal();

        // RSA 방식 아니고 Hash 암호 방식
        String jwtToken = JWT.create()
                .withSubject(principalDetailis.getUsername())  // 토큰 이름 (member의 id)
                .withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME)) // 토큰 만료 시간
                .withClaim("name", principalDetailis.getUser().getName()) // 내용
                .withClaim("role", principalDetailis.getRole())
//                .withClaim("email", principalDetailis.getUser().getEmail())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET)); // signature 서버만 아는 고유한 값 secret 넣기
        System.out.println("jwtToken = " + jwtToken);
//        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtToken); // 클러이언트가 요청한 로그인에 대해 응답할 때 header에 토큰값을 넣음
//        response.addHeader(JwtProperties.HEADER_STRING, jwtToken); // 클러이언트가 요청한 로그인에 대해 응답할 때 header에 토큰값을 넣음
        String encodeToken = URLEncoder.encode(jwtToken, "utf-8");
        System.out.println("encodeToken = " + encodeToken);
        Cookie cookieToken = new Cookie(JwtProperties.HEADER_STRING, encodeToken);
        cookieToken.setMaxAge(JwtProperties.EXPIRATION_TIME / 1000);
        cookieToken.setHttpOnly(true);
        cookieToken.setPath("/");
        response.addCookie(cookieToken);
        // 쿠키 저장 후 다시 꺼내서 samesite 적용
        String header = response.getHeader(HttpHeaders.SET_COOKIE);
        response.setHeader(HttpHeaders.SET_COOKIE, String.format("%s; Secure; %s", header, "SameSite=None"));
    }

}
