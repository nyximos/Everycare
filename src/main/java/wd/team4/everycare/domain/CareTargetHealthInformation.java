package wd.team4.everycare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "care_target_health_information")
@SequenceGenerator(name = "care_target_health_information_seq_generator",
        sequenceName = "care_target_health_information_seq",
        initialValue = 1, allocationSize = 1)
public class CareTargetHealthInformation {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "care_target_health_information_seq_generator")
    @Column(name = "care_target_health_information_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "care_target_id")
    private CareTarget careTarget;

    @ManyToOne
    @JoinColumn(name = "health_information")
    private HealthInformation healthInformation;

}
