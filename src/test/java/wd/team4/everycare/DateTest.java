package wd.team4.everycare;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.Dates;
import wd.team4.everycare.repository.TestRepository;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)  // spring이랑 같이 엮어서 실행, 스프링과 테스트 통합
@SpringBootTest               // springBoot 띄운 상태에서 테스트, 이게 없으면 @Autowired 다 실패
@Transactional                // 변경 작업할 때 필수, 테스트 끝나면 트랜잭션 강제로 롤백, 반복 가능한 테스트 지원public class DateTest {
public class DateTest {

    @Autowired
    TestRepository testRepository;
    @Autowired
    EntityManager em;

    @Test
    @Rollback(false)
    public void save(){
        String string = "2022-05-21";
        LocalDate date = LocalDate.parse(string, DateTimeFormatter.ISO_DATE);
        Dates test = new Dates(1L, date);

        testRepository.save(test);
    }

    @Test
    @Rollback(false)
    public void comparison() throws ParseException {
        Dates dates = new Dates(1L, LocalDate.now());
        testRepository.save(dates);

        em.flush();
        em.clear();

        LocalDate today = LocalDate.now();
        Dates dates1 = testRepository.findByDate2(today).get();
        System.out.println("dates1 = " + dates1);


        /*
        LocalDate today = LocalDate.now();
        System.out.println("today = " + today);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

        String string = today.format(formatter);


        Date startDatetime = new SimpleDateFormat("yyyy-MM-dd").parse(string);



        Dates date = testRepository.findByDate(startDatetime);
        date.toString();
         */


        /*
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

        String string = today.format(formatter);

        Dates date = testRepository.findByDate(string);
        date.toString();
         */

    }
    }