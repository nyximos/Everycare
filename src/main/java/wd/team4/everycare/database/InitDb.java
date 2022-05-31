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
    private final InitBoardService boardService;
    private final InitJobOfferService jobOfferService;
    private final InitContractService contractService;
    private final InitOrderService orderService;
    private final InitHealthClassificationService healthClassificationService;
    private final InitCareNoteService careNoteService;
    private final InitCareSitterReviewService careSitterReviewService;
    private final InitBadgeService badgeService;

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
        boardService.dbInit();
        jobOfferService.dbInit();
        contractService.dbInit();
        orderService.dbInit();
        healthClassificationService.dbInIt();
        careSitterReviewService.dbInit();
        badgeService.dbInit();
//        careNoteService.dbInit();
    }
}