package wd.team4.everycare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "care_target")
@SequenceGenerator(name = "care_target_seq_generator",
        sequenceName = "care_target_seq",
        initialValue = 1, allocationSize = 1)
public class CareTarget {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "care_target_seq_generator")
    @Column(name = "care_target_id", nullable = false)
    private Long id;

    @Column(name = "care_target_name", length = 30, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "care_target_gender", nullable = false)
    private Gender gender;

    @Column(name = "care_target_birth", nullable = false)
    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate birth;

    @Column(name = "care_target_height", nullable = false)
    private Long height;

    @Column(name = "care_target_weight", nullable = false)
    private Long weight;

    @Column(name = "care_target_zipcode", length = 45, nullable = false)
    private String zipcode;

    @Column(name = "care_target_address", length = 255, nullable = false)
    private String address;

    @Column(name = "care_target_detailed_address", length = 255, nullable = false)
    private String detailedAddress;

    @Column(name = "care_target_long_term_care_grade", nullable = false)
    private int longTermCareGrade;

    @Column(name = "care_target_comment", length = 500)
    private String comment;

    @Column(name = "care_target_pet", nullable = false)
    private int pet;

    @Column(name = "care_target_is_cctv_agrement", nullable = false)
    private int isCctvAgrement;

    @Column(name = "care_target_care_tjype", length = 50, nullable = false)
    private CareType careType;

    @Column(name = "care_target_corona_test", nullable = false)
    private int coronaTest;

    @OneToMany
    @JoinColumn(name = "care_target_health_information_id")
    private List<CareTargetHealthInformation> careTargetHealthInformations;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "careTarget")
    List<HealthRecord> healthRecords = new ArrayList<>();

    @OneToMany(mappedBy = "careTarget")
    List<CareTargetSchedule> careTargetSchedules = new ArrayList<>();

}
