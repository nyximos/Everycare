package wd.team4.everycare.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "care_target_health_information")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "care_target_health_information_seq_generator",
        sequenceName = "care_target_health_information_seq",
        initialValue = 1, allocationSize = 1)
public class CareTargetHealthInformation {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "care_target_health_information_seq_generator")
    @Column(name = "care_target_health_information_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_target_id")
    private CareTarget careTarget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_information_id")
    private HealthInformation healthInformation;

}
