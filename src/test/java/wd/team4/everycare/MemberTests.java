package wd.team4.everycare;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import wd.team4.everycare.config.SecurityConfig;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.repository.MemberRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import static wd.team4.everycare.domain.ActivityStatus.ACTIVE;
import static wd.team4.everycare.domain.Gender.M;

@SpringBootTest
public class MemberTests {

    private MemberRepository memberRepository;

//    private PasswordEncoder passwordEncoder;
    
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Test
    public void insertDummies(){
        /*
            1-90 MEMBER
            91-100 ADMIN
         */
        System.out.println(passwordEncoder().encode("1111"));

        IntStream.rangeClosed(1,100).forEach(i -> {
            Member member = Member.builder()
                    .id(String.valueOf(i))
                    .password(passwordEncoder().encode("1111"))
                    .name("사용자"+i)
                    .gender(M)
                    .birth(LocalDate.now())
                    .phone("01011112222")
                    .email("user"+i+"@everycare.org")
                    .createdAt(LocalDateTime.now())
                    .activityStatus(ACTIVE)
                    .zipcode("12345")
                    .address("주소")
                    .addressDetail("상세주소")
                    .build();

            System.out.println("member = " + member);
        });
    }
}
