package wd.team4.everycare.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.repository.MemberRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Optional;

// 인가
public class JwtAuthorizationFilter extends BasicAuthenticationFilter{

    private MemberRepository memberRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // ==== 쿠키 방식 === //
        System.out.println("인증이 필요한 주소 요청됨 1");
        Cookie[] cookies = request.getCookies();
        String encodeToken = "";
        if(cookies == null) {
            System.out.println("실패");
            chain.doFilter(request, response);
            return;
        }
        for(Cookie c : cookies) {
            if (c.getName().equals(JwtProperties.HEADER_STRING)) {
                encodeToken = c.getValue();
            }
        }
        System.out.println("encodeToken = " + encodeToken);
        String accessToken = URLDecoder.decode(encodeToken, "utf-8");
        System.out.println("accessToken = " + accessToken);
//        String username1 = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(accessToken)
//                .getClaim("username").asString();
        String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(accessToken)
                .getSubject();
        if(username != null) {
            Optional<Member> member1 = memberRepository.findById(username);

            // 인증은 토큰 검증시 끝. 인증을 하기 위해서가 아닌 스프링 시큐리티가 수행해주는 권한 처리를 위해
            // 아래와 같이 토큰을 만들어서 Authentication 객체를 강제로 만들고 그걸 세션에 저장!
            PrincipalDetails principalDetails = new PrincipalDetails(member1.get());
            Authentication authentication1 =
                    new UsernamePasswordAuthenticationToken(
                            principalDetails, //나중에 컨트롤러에서 DI해서 쓸 때 사용하기 편함.
                            null, // 패스워드는 모르니까 null 처리, 어차피 지금 인증하는게 아니니까!!
                            principalDetails.getAuthorities());

            // 강제로 시큐리티의 세션에 접근하여 값 저장
            SecurityContextHolder.getContext().setAuthentication(authentication1);
        }
        System.out.println("완료");
        chain.doFilter(request, response);

        /*
        // === 기존 방식 === //
        System.out.println("인증이 필요한 주소 요청됨 2");
        String header = request.getHeader(JwtProperties.HEADER_STRING);
//        if(header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
        if(header == null) {
            chain.doFilter(request, response);
            return;
        }
        System.out.println("header : "+header);
//        String token = request.getHeader(JwtProperties.HEADER_STRING)
//                .replace(JwtProperties.TOKEN_PREFIX, "");
        String token = request.getHeader(JwtProperties.HEADER_STRING);

        // 토큰 검증 (이게 인증이기 때문에 AuthenticationManager도 필요 없음)
        // 내가 SecurityContext에 직접접근해서 세션을 만들때 자동으로 UserDetailsService에 있는 loadByUsername이 호출됨.
        String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
                .getClaim("username").asString();

        if(username != null) {
            Optional<Member> member = memberRepository.findById(username);

            // 인증은 토큰 검증시 끝. 인증을 하기 위해서가 아닌 스프링 시큐리티가 수행해주는 권한 처리를 위해
            // 아래와 같이 토큰을 만들어서 Authentication 객체를 강제로 만들고 그걸 세션에 저장!
            PrincipalDetails principalDetails = new PrincipalDetails(member.get());
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            principalDetails, //나중에 컨트롤러에서 DI해서 쓸 때 사용하기 편함.
                            null, // 패스워드는 모르니까 null 처리, 어차피 지금 인증하는게 아니니까!!
                            principalDetails.getAuthorities());

            // 강제로 시큐리티의 세션에 접근하여 값 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        System.out.println("완료");
        chain.doFilter(request, response);
         */

    }



}
