package wd.team4.everycare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "hopeful_region_seq_generator",
        sequenceName = "hopeful_region_seq",
        initialValue = 1, allocationSize = 1)

public class HopefulRegion {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hopeful_region_seq_generator")
    @Column(name = "hopeful_region_id")
    private Long id;

    @Column(name = "hopeful_region_name", length = 100, nullable = false)
    private String name;

    @Column(name = "hopeful_region_level", length = 10, nullable = false)
    private String regionLevel;

    @ManyToOne
    @JoinColumn(name = "hopeful_region_id2")
    private HopefulRegion hopefulRegion;
}
