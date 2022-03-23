package wd.team4.everycare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EverycareApplication {

    public static void main(String[] args) {
        SpringApplication.run(EverycareApplication.class, args);
    }

}
