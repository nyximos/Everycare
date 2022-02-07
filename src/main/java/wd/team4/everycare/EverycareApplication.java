package wd.team4.everycare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class EverycareApplication {

    public static void main(String[] args) {
        SpringApplication.run(EverycareApplication.class, args);
    }

}
