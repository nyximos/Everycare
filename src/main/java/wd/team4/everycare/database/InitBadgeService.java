package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.ActivityClassification;
import wd.team4.everycare.domain.Bedge;
import wd.team4.everycare.repository.ActivityClassificationRepository;

import javax.persistence.EntityManager;

@Component
@Transactional
@RequiredArgsConstructor
public class InitBadgeService {

    private final EntityManager em;
    private final ActivityClassificationRepository activityClassificationRepository;

    public void dbInit(){
        ActivityClassification activityClassification1 = activityClassificationRepository.findById(1L).get();
        ActivityClassification activityClassification2 = activityClassificationRepository.findById(2L).get();
        ActivityClassification activityClassification3 = activityClassificationRepository.findById(3L).get();
        ActivityClassification activityClassification4 = activityClassificationRepository.findById(4L).get();
        ActivityClassification activityClassification5 = activityClassificationRepository.findById(5L).get();
        ActivityClassification activityClassification6 = activityClassificationRepository.findById(6L).get();
        ActivityClassification activityClassification7 = activityClassificationRepository.findById(7L).get();
        ActivityClassification activityClassification8 = activityClassificationRepository.findById(8L).get();
        ActivityClassification activityClassification9 = activityClassificationRepository.findById(9L).get();
        ActivityClassification activityClassification10 = activityClassificationRepository.findById(10L).get();
        ActivityClassification activityClassification11 = activityClassificationRepository.findById(11L).get();
        ActivityClassification activityClassification12 = activityClassificationRepository.findById(12L).get();
        ActivityClassification activityClassification13 = activityClassificationRepository.findById(13L).get();
        ActivityClassification activityClassification14 = activityClassificationRepository.findById(14L).get();
        ActivityClassification activityClassification15 = activityClassificationRepository.findById(15L).get();
        ActivityClassification activityClassification16 = activityClassificationRepository.findById(16L).get();
        ActivityClassification activityClassification17 = activityClassificationRepository.findById(17L).get();
        ActivityClassification activityClassification18 = activityClassificationRepository.findById(18L).get();
        ActivityClassification activityClassification19 = activityClassificationRepository.findById(19L).get();
        ActivityClassification activityClassification20 = activityClassificationRepository.findById(20L).get();
        ActivityClassification activityClassification21 = activityClassificationRepository.findById(21L).get();
        ActivityClassification activityClassification22 = activityClassificationRepository.findById(22L).get();
        ActivityClassification activityClassification23 = activityClassificationRepository.findById(23L).get();
        ActivityClassification activityClassification24 = activityClassificationRepository.findById(24L).get();
        ActivityClassification activityClassification25 = activityClassificationRepository.findById(25L).get();
        ActivityClassification activityClassification26 = activityClassificationRepository.findById(26L).get();
        ActivityClassification activityClassification27 = activityClassificationRepository.findById(27L).get();
        ActivityClassification activityClassification28 = activityClassificationRepository.findById(28L).get();
        ActivityClassification activityClassification29 = activityClassificationRepository.findById(29L).get();
        ActivityClassification activityClassification30 = activityClassificationRepository.findById(30L).get();
        ActivityClassification activityClassification31 = activityClassificationRepository.findById(31L).get();
        ActivityClassification activityClassification32 = activityClassificationRepository.findById(32L).get();
        ActivityClassification activityClassification33 = activityClassificationRepository.findById(33L).get();
        ActivityClassification activityClassification34 = activityClassificationRepository.findById(34L).get();
        ActivityClassification activityClassification35 = activityClassificationRepository.findById(35L).get();
        ActivityClassification activityClassification36 = activityClassificationRepository.findById(36L).get();
        ActivityClassification activityClassification37 = activityClassificationRepository.findById(37L).get();
        ActivityClassification activityClassification38 = activityClassificationRepository.findById(38L).get();
        ActivityClassification activityClassification39 = activityClassificationRepository.findById(39L).get();
        ActivityClassification activityClassification40 = activityClassificationRepository.findById(40L).get();
        ActivityClassification activityClassification41 = activityClassificationRepository.findById(41L).get();
        ActivityClassification activityClassification42 = activityClassificationRepository.findById(42L).get();
        ActivityClassification activityClassification43 = activityClassificationRepository.findById(43L).get();
        ActivityClassification activityClassification44 = activityClassificationRepository.findById(44L).get();
        ActivityClassification activityClassification45 = activityClassificationRepository.findById(45L).get();
        ActivityClassification activityClassification46 = activityClassificationRepository.findById(46L).get();
        ActivityClassification activityClassification47 = activityClassificationRepository.findById(47L).get();
        ActivityClassification activityClassification48 = activityClassificationRepository.findById(48L).get();
        ActivityClassification activityClassification49 = activityClassificationRepository.findById(49L).get();
        ActivityClassification activityClassification50 = activityClassificationRepository.findById(50L).get();
        ActivityClassification activityClassification51 = activityClassificationRepository.findById(51L).get();
        ActivityClassification activityClassification52 = activityClassificationRepository.findById(52L).get();
        ActivityClassification activityClassification53 = activityClassificationRepository.findById(53L).get();
        ActivityClassification activityClassification54 = activityClassificationRepository.findById(54L).get();
        ActivityClassification activityClassification55 = activityClassificationRepository.findById(55L).get();
        ActivityClassification activityClassification56 = activityClassificationRepository.findById(56L).get();
        ActivityClassification activityClassification57 = activityClassificationRepository.findById(57L).get();
        ActivityClassification activityClassification58 = activityClassificationRepository.findById(58L).get();
        ActivityClassification activityClassification59 = activityClassificationRepository.findById(59L).get();
        ActivityClassification activityClassification60 = activityClassificationRepository.findById(60L).get();
        ActivityClassification activityClassification61 = activityClassificationRepository.findById(61L).get();
        ActivityClassification activityClassification62 = activityClassificationRepository.findById(62L).get();
        ActivityClassification activityClassification63 = activityClassificationRepository.findById(63L).get();
        ActivityClassification activityClassification64 = activityClassificationRepository.findById(64L).get();
        ActivityClassification activityClassification65 = activityClassificationRepository.findById(65L).get();
        ActivityClassification activityClassification66 = activityClassificationRepository.findById(66L).get();
        ActivityClassification activityClassification67 = activityClassificationRepository.findById(67L).get();
        ActivityClassification activityClassification68 = activityClassificationRepository.findById(68L).get();
        ActivityClassification activityClassification69 = activityClassificationRepository.findById(69L).get();
        ActivityClassification activityClassification70 = activityClassificationRepository.findById(70L).get();
        ActivityClassification activityClassification71 = activityClassificationRepository.findById(71L).get();
        ActivityClassification activityClassification72 = activityClassificationRepository.findById(72L).get();
        ActivityClassification activityClassification73 = activityClassificationRepository.findById(73L).get();

        Bedge badge1 = createBadge("old-person.png", "old-person.png", activityClassification1);
        Bedge badge2 = createBadge("children.png", "children.png", activityClassification2);
        Bedge badge3 = createBadge("mother.png", "mother.png", activityClassification3);
        Bedge badge4 = createBadge("hospitalisation.png", "hospitalisation.png", activityClassification4);
        Bedge badge5 = createBadge("brainstorm.png", "brainstorm.png", activityClassification5);
        Bedge badge6 = createBadge("cognitive.png", "cognitive.png", activityClassification6);
        Bedge badge7 = createBadge("affective.png", "affective.png", activityClassification7);
        Bedge badge8 = createBadge("school-bus.png", "school-bus.png", activityClassification8);
        Bedge badge9 = createBadge("knowledge.png", "knowledge.png", activityClassification9);
        Bedge badge10 = createBadge("playing.png", "playing.png", activityClassification10);
        Bedge badge11 = createBadge("newborn.png", "newborn.png", activityClassification11);
        Bedge badge12 = createBadge("mom.png", "mom.png", activityClassification12);
        Bedge badge13 = createBadge("physiotherapy.png", "physiotherapy.png", activityClassification13);
        Bedge badge14 = createBadge("brainstorm.png", "brainstorm.png", activityClassification14);
        Bedge badge15 = createBadge("home.png", "home.png", activityClassification15);
        Bedge badge16 = createBadge("life.png", "life.png", activityClassification16);
        Bedge badge17 = createBadge("jogging.png", "jogging.png", activityClassification17);
        Bedge badge18 = createBadge("cooking.png", "cooking.png", activityClassification18);
        Bedge badge19 = createBadge("clean-clothes.png", "clean-clothes.png", activityClassification19);
        Bedge badge20 = createBadge("sketch.png", "sketch.png", activityClassification20);
        Bedge badge21 = createBadge("puzzle.png", "puzzle.png", activityClassification21);
        Bedge badge22 = createBadge("flower-playing-cards.png", "flower-playing-cards.png", activityClassification22);
        Bedge badge23 = createBadge("smartphone.png", "smartphone.png", activityClassification23);
        Bedge badge24 = createBadge("internet.png", "internet.png", activityClassification24);
        Bedge badge25 = createBadge("worrying.png", "worrying.png", activityClassification25);
        Bedge badge26 = createBadge("talking.png", "talking.png", activityClassification26);
        Bedge badge27 = createBadge("school-bus.png", "school-bus.png", activityClassification27);
        Bedge badge28 = createBadge("school-bus.png", "school-bus.png", activityClassification28);
        Bedge badge29 = createBadge("sience.png", "sience.png", activityClassification29);
        Bedge badge30 = createBadge("english.png", "english.png", activityClassification30);
        Bedge badge31 = createBadge("korean.png", "korean.png", activityClassification31);
        Bedge badge32 = createBadge("haveruta.png", "haveruta.png", activityClassification32);
        Bedge badge33 = createBadge("books.png", "books.png", activityClassification33);
        Bedge badge34 = createBadge("board-game.png", "board-game.png", activityClassification34);
        Bedge badge35 = createBadge("sing.png", "sing.png", activityClassification35);
        Bedge badge36 = createBadge("radio.png", "radio.png", activityClassification36);
        Bedge badge37 = createBadge("doll.png", "doll.png", activityClassification37);
        Bedge badge38 = createBadge("checklist.png", "checklist.png", activityClassification38);
        Bedge badge39 = createBadge("blocks.png", "blocks.png", activityClassification39);
        Bedge badge40 = createBadge("art.png", "art.png", activityClassification40);
        Bedge badge41 = createBadge("playground.png", "playground.png", activityClassification41);
        Bedge badge42 = createBadge("newborn.png", "newborn.png", activityClassification42);
        Bedge badge43 = createBadge("massage.png", "massage.png", activityClassification43);
        Bedge badge44 = createBadge("clean-food.png", "clean-food.png", activityClassification44);
        Bedge badge45 = createBadge("breathing.png", "breathing.png", activityClassification45);
        Bedge badge46 = createBadge("stretching.png", "stretching.png", activityClassification46);
        Bedge badge47 = createBadge("breastfeeding.png", "breastfeeding.png", activityClassification47);
        Bedge badge48 = createBadge("physiotherapy.png", "physiotherapy.png", activityClassification48);
        Bedge badge49 = createBadge("knowledge.png", "knowledge.png", activityClassification49);
        Bedge badge50 = createBadge("broom.png", "broom.png", activityClassification50);
        Bedge badge51 = createBadge("wiping.png", "wiping.png", activityClassification51);
        Bedge badge52 = createBadge("window.png", "window.png", activityClassification52);
        Bedge badge53 = createBadge("dust.png", "dust.png", activityClassification53);
        Bedge badge54 = createBadge("washing-clothes.png", "washing-clothes.png", activityClassification54);
        Bedge badge55 = createBadge("washing-clothes.png", "washing-clothes.png", activityClassification55);
        Bedge badge56 = createBadge("meal.png", "meal.png", activityClassification56);
        Bedge badge57 = createBadge("meal.png", "meal.png", activityClassification57);
        Bedge badge58 = createBadge("shopping.png", "shopping.png", activityClassification58);
        Bedge badge59 = createBadge("snack.png", "snack.png", activityClassification59);
        Bedge badge60 = createBadge("Ironing.png", "Ironing.png", activityClassification60);
        Bedge badge61 = createBadge("move.png", "move.png", activityClassification61);
        Bedge badge62 = createBadge("wheel-chair.png", "wheel-chair.png", activityClassification62);
        Bedge badge63 = createBadge("wash-face.png", "wash-face.png", activityClassification63);
        Bedge badge64 = createBadge("brush-teeth.png", "brush-teeth.png", activityClassification64);
        Bedge badge65 = createBadge("shower.png", "shower.png", activityClassification65);
        Bedge badge66 = createBadge("hair-washing.png", "hair-washing.png", activityClassification66);
        Bedge badge67 = createBadge("foodservice.png", "foodservice.png", activityClassification67);
        Bedge badge68 = createBadge("clothes.png", "clothes.png", activityClassification68);
        Bedge badge69 = createBadge("movement.png", "movement.png", activityClassification69);
        Bedge badge70 = createBadge("walk.png", "walk.png", activityClassification70);
        Bedge badge71 = createBadge("exercise.png", "exercise.png", activityClassification71);
        Bedge badge72 = createBadge("exercising.png", "exercising.png", activityClassification72);
        Bedge badge73 = createBadge("medicine.png", "medicine.png", activityClassification73);
    }

    private Bedge createBadge(String fileName, String filePath, ActivityClassification activityClassification){
        Bedge badge = Bedge.builder()
                .fileName(fileName)
                .filePath(filePath)
                .activityClassification(activityClassification)
                .build();

        em.persist(badge);

        return badge;
    }
}
