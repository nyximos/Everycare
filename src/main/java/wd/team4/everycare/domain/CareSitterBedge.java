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
@Table(name = "care_sitter_bedge")
@SequenceGenerator(name = "care_sitter_bedge_seq_generator",
        sequenceName = "care_sitter_bedge_seq",
        initialValue = 1, allocationSize = 1)
public class CareSitterBedge {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "care_sitter_bedge_seq_generator")
    @Column(name = "care_sitter_bedge_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "care_sitter_id")
    private CareSitter careSitter;

    @ManyToOne
    @JoinColumn(name = "bedge_id")
    private Bedge bedge;
}
