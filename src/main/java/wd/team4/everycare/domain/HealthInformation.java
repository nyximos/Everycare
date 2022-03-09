package wd.team4.everycare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "health_information")
@SequenceGenerator(name = "health_information_seq_generator",
        sequenceName = "health_information_seq",
        initialValue = 1, allocationSize = 1)
public class HealthInformation {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "health_information_seq_generator")
    @Column(name = "health_information_id", nullable = false)
    private Long id;

    @Column(name = "health_information_name", nullable = false)
    private String name;

    @OneToMany
    @JoinColumn(name = "care_target_health_information_id")
    private List<CareTargetHealthInformation> careTargetHealthInformations;

}
