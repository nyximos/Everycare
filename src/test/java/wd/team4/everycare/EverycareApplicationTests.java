package wd.team4.everycare;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EverycareApplicationTests {

    @Test
    void contextLoads() {

        String springVersion = org.springframework.core.SpringVersion.getVersion();
        String springSecurity = org.springframework.security.core.SpringSecurityCoreVersion.getVersion();

        System.out.println("스프링 프레임워크 버전 : " + springVersion);
        System.out.println("springSecurity = " + springSecurity);
    }

}
