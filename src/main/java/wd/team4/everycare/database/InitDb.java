package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitMemberService memberService;
    private final InitCareSitterService careSitterService;
    private final InitStoreService storeService;
    private final InitCareTargetService careTargetService;
    private final InitProductCategoryService productCategoryService;
    private final InitActivityClassificationService activityClassification;
    private final InitCareTargetScheduleService careTargetScheduleService;
    private final InitProductService productService;

    @PostConstruct
    public void init() {
        memberService.dbInit();
        careTargetService.dbInit();
        activityClassification.dbInit();
        careTargetScheduleService.dbInit();
        careSitterService.dbInit();
        storeService.dbInit();
        productCategoryService.dbInit();
        productService.dbInit();
    }
}
