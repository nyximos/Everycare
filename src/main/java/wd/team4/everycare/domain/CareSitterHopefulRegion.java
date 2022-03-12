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
@Table(name = "care_sitter_hopeful_region")
@SequenceGenerator(name = "care_sitter_hopeful_region_seq_generator",
        sequenceName = "care_sitter_hopeful_region_seq",
        initialValue = 1, allocationSize = 1)
public class CareSitterHopefulRegion {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "care_sitter_hopeful_region_seq")
    @Column(name = "care_sitter_hopeful_region_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "care_sitter_id")
    private CareSitter careSitter;

    @ManyToOne
    @JoinColumn(name = "hopeful_region_id")
    private HopefulRegion hopefulRegion;
}
