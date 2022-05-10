package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitMemberService memberService;
    private final InitCareTargetService careTargetService;

    @PostConstruct
    public void init() {
        memberService.dbInit();
        careTargetService.dbInit();
    }
}
