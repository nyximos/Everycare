package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.ActivityClassification;

import javax.persistence.EntityManager;

@Component
@Transactional
@RequiredArgsConstructor
public class InitActivityClassification {

    private final EntityManager em;

    public void dbInit() {
        ActivityClassification activityInformation1 = createParentActivityInformation("노인", 1);
        ActivityClassification activityInformation2 = createParentActivityInformation("아동", 1);
        ActivityClassification activityInformation3 = createParentActivityInformation("산모", 1);
        ActivityClassification activityInformation4 = createParentActivityInformation("환자", 1);
        ActivityClassification activityInformation5 = createParentActivityInformation("공통", 1);

        ActivityClassification childActivityInformation;
        ActivityClassification childActivityInformation1 = createChildActivityInformation("인지지원", 2, activityInformation1);
        ActivityClassification childActivityInformation2 = createChildActivityInformation("정서지원", 2, activityInformation1);
        ActivityClassification childActivityInformation3 = createChildActivityInformation("등하원", 2, activityInformation2);
        ActivityClassification childActivityInformation4 = createChildActivityInformation("학습", 2, activityInformation2);
        ActivityClassification childActivityInformation5 = createChildActivityInformation("놀이", 2, activityInformation2);
        ActivityClassification childActivityInformation6 = createChildActivityInformation("신생아돌봄", 2, activityInformation3);
        ActivityClassification childActivityInformation7 = createChildActivityInformation("산후조리", 2, activityInformation3);
        ActivityClassification childActivityInformation8 = createChildActivityInformation("재활운동", 2, activityInformation4);
        ActivityClassification childActivityInformation9 = createChildActivityInformation("인지활동", 2, activityInformation4);
        ActivityClassification childActivityInformation10 = createChildActivityInformation("가사", 2, activityInformation5);
        ActivityClassification childActivityInformation11 = createChildActivityInformation("일상생활", 2, activityInformation5);
        ActivityClassification childActivityInformation12 = createChildActivityInformation("신체활동", 2, activityInformation5);

        createChildActivityInformation("요리", 3, childActivityInformation1);
        createChildActivityInformation("옷 개기", 3, childActivityInformation1);
        createChildActivityInformation("그림 그리기", 3, childActivityInformation1);
        createChildActivityInformation("퍼즐 맞추기", 3, childActivityInformation1);
        createChildActivityInformation("화투", 3, childActivityInformation1);

        createChildActivityInformation("스마트폰", 3, childActivityInformation2);
        createChildActivityInformation("인터넷", 3, childActivityInformation2);
        createChildActivityInformation("고민 나눔", 3, childActivityInformation2);
        createChildActivityInformation("가족 소통", 3, childActivityInformation2);


        createChildActivityInformation("등원", 3, childActivityInformation3);
        createChildActivityInformation("하원", 3, childActivityInformation3);
        createChildActivityInformation("과학", 3, childActivityInformation4);
        createChildActivityInformation("영어", 3, childActivityInformation4);
        createChildActivityInformation("한글", 3, childActivityInformation4);
        createChildActivityInformation("하브루타", 3, childActivityInformation4);
        createChildActivityInformation("책", 3, childActivityInformation5);
        createChildActivityInformation("보드게임", 3, childActivityInformation5);
        createChildActivityInformation("노래", 3, childActivityInformation5);
        createChildActivityInformation("라디오", 3, childActivityInformation5);
        createChildActivityInformation("인형", 3, childActivityInformation5);
        createChildActivityInformation("교구", 3, childActivityInformation5);
        createChildActivityInformation("블럭", 3, childActivityInformation5);
        createChildActivityInformation("미술", 3, childActivityInformation5);
        createChildActivityInformation("놀이터", 3, childActivityInformation5);

        createChildActivityInformation("신생아 돌봄", 3, childActivityInformation6);
        createChildActivityInformation("산후마사지", 3, childActivityInformation7);
        createChildActivityInformation("산후용 음식준비", 3, childActivityInformation7);
        createChildActivityInformation("호흡법", 3, childActivityInformation7);
        createChildActivityInformation("스트레칭", 3, childActivityInformation7);
        createChildActivityInformation("수유", 3, childActivityInformation7);

        createChildActivityInformation("재활운동", 3, childActivityInformation8);
        createChildActivityInformation("인지활동", 3, childActivityInformation9);

        createChildActivityInformation("쓸기", 3, childActivityInformation10);
        createChildActivityInformation("닦기", 3, childActivityInformation10);
        createChildActivityInformation("환기", 3, childActivityInformation10);
        createChildActivityInformation("먼지털기", 3, childActivityInformation10);
        createChildActivityInformation("빨래", 3, childActivityInformation10);
        createChildActivityInformation("식사도움", 3, childActivityInformation10);
        createChildActivityInformation("장보기", 3, childActivityInformation10);
        createChildActivityInformation("밥짓기", 3, childActivityInformation10);
        createChildActivityInformation("설거지", 3, childActivityInformation10);
        createChildActivityInformation("간식", 3, childActivityInformation10);
        createChildActivityInformation("다림질", 3, childActivityInformation10);

        createChildActivityInformation("이동", 3, childActivityInformation11);
        createChildActivityInformation("병원 동행", 3, childActivityInformation11);

        createChildActivityInformation("세면", 3, childActivityInformation12);
        createChildActivityInformation("양치", 3, childActivityInformation12);
        createChildActivityInformation("목욕", 3, childActivityInformation12);
        createChildActivityInformation("머리 감기기", 3, childActivityInformation12);
        createChildActivityInformation("식사", 3, childActivityInformation12);
        createChildActivityInformation("환복", 3, childActivityInformation12);
        createChildActivityInformation("자세변경", 3, childActivityInformation12);
        createChildActivityInformation("산책", 3, childActivityInformation12);
        createChildActivityInformation("운동", 3, childActivityInformation12);
        createChildActivityInformation("스트레칭", 3, childActivityInformation12);
        createChildActivityInformation("복약", 3, childActivityInformation12);
    }

    private ActivityClassification createParentActivityInformation(String name, int level) {
        ActivityClassification parentActivityClassification = ActivityClassification.builder()
                .name(name)
                .level(level)
                .build();
        em.persist(parentActivityClassification);
        return  parentActivityClassification;
    }

    private ActivityClassification createChildActivityInformation(String name, int level, ActivityClassification activityClassification){
        ActivityClassification childActivityClassification = ActivityClassification.builder()
                .name(name)
                .level(level)
                .activityClassification(activityClassification)
                .build();
        em.persist(childActivityClassification);
        return childActivityClassification;
    }

}
