package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.ActivityStatus;
import wd.team4.everycare.domain.Gender;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.domain.MemberRole;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Component
@Transactional
@RequiredArgsConstructor
public class InitMemberService {

    private final EntityManager em;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private Random random = new Random();

    private MemberRole member = MemberRole.ROLE_MEMBER;
    private MemberRole admin = MemberRole.ROLE_ADMIN;
    private Gender m = Gender.M;
    private Gender w = Gender.W;
    private ActivityStatus active = ActivityStatus.ACTIVE;

    private String[] name = new String[]{"관리자","허윤정", "고승희", "류예원", "설서준", "복윤빈", "조희수", "안규현", "전승철", "배현주", "정진영", "서고은", "예소연", "하영환", "임은정", "서민호"};
    private String[] birth = new String[]{"1988-01-11", "1989-01-25", "1990-02-21", "1991-03-05", "1977-04-10",
                                            "1974-05-22", "1968-06-19", "1978-12-25", "1979-07-30", "1980-08-13",
                                            "1981-09-01", "1973-10-28", "1974-11-08", "1988-07-10", "1971-02-06", "1983-03-29"};
    private Gender[] gender = new Gender[]{w,w,m,w,m,w,w,m,m,w,m,w,w,m,w,m};
    private String[] phone = new String[]{"010-0000-0000","010-1111-1111","010-2222-2222","010-3333-3333", "010-4444-4444",
                                            "010-5555-5555", "010-6666-6666", "010-7777-7777", "010-8888-8888", "010-9999-9999",
                                            "010-1234-1234", "010-2345-3456", "010-5678-7890", "010-7543-5432", "010-9879-5786", "010-7234-1296"};
    private String[] email = new String[]{"aa@aa", "bb@bb", "cc@cc", "dd@dd", "ee@ee",
                                            "ff@ff", "gg@gg", "hh@hh", "ii@ii", "jj@jj",
                                            "kk@kk", "ll@ll", "mm@mm", "nn@nn", "oo@oo", "pp@pp"};
    private String[] zipcode = new String[]{"12345", "23456", "65436","13214","76548",
                                            "76588", "92652", "42346", "23456", "38348",
                                            "24858", "43272", "45838", "92821","32781","14123"};

    public void dbInit() {
        int adminAccount = this.random.nextInt();
        if(adminAccount<0){
            adminAccount *= -1;
        }

        Member admin = createMember("admin", "admin", name[0], this.admin, gender[0], birth[0], phone[0], email[0],
                LocalDateTime.now(), active, zipcode[0], "대구광역시", "동구", "농협", String.valueOf(adminAccount));
        admin.registrationAdmin(LocalDateTime.now());
        em.persist(admin);

        for (int i=1; i<16; i++){
            int account = this.random.nextInt();
            if(account<0){
                account *= -1;
            }
            Member member = createMember("member" + i, String.valueOf(i), name[i], this.member, gender[i], birth[i], phone[i], email[i], LocalDateTime.now(),
                    active, zipcode[i], "대구광역시", "북구",  "농협", String.valueOf(account));
            em.persist(member);
        }
    }

    private Member createMember(String id, String password, String name, MemberRole role, Gender gender, String birth, String phone, String email,
                                LocalDateTime createdAt, ActivityStatus activityStatus, String zipcode, String address, String detailedAddress,
                                String bank, String accountNumber) {
        Member member = Member.builder()
                .id(id)
                .password(bCryptPasswordEncoder.encode(password))
                .name(name)
                .role(role)
                .gender(gender)
                .birth(LocalDate.parse(birth))
                .phone(phone)
                .email(email)
                .createdAt(createdAt)
                .activityStatus(activityStatus)
                .zipcode(zipcode)
                .address(address)
                .detailedAddress(detailedAddress)
                .bank(bank)
                .accountNumber(accountNumber)
                .build();
        return member;
    }

}
