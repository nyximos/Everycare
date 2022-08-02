package wd.team4.everycare.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wd.team4.everycare.domain.ActivityStatus;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.service.exception.LoginException;

import java.util.Optional;

// 시큐리티 설정에서 loginProcessingUrl("/login");
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public PrincipalDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("PrincipalDetailsService : DB에 유저 있는지 확인 ");
        Optional<Member> member = memberRepository.findById(username);

        // 활동 정지된 멤버 로그인 실패
        Member memberEntity = member.orElse(null);

        if (member == null) {
            throw new LoginException(username);
        }

        ActivityStatus activityStatus = memberEntity.getActivityStatus();
        if (activityStatus == ActivityStatus.STOP) {
            throw new LoginException();
        }

        // session.setAttribute("loginUser", user);
        return new PrincipalDetails(member.get());
    }
}
