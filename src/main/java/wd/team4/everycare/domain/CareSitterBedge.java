package wd.team4.everycare.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "care_sitter_bedge")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "care_sitter_bedge_seq_generator",
        sequenceName = "care_sitter_bedge_seq",
        initialValue = 1, allocationSize = 1)
public class CareSitterBedge {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "care_sitter_bedge_seq_generator")
    @Column(name = "care_sitter_bedge_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_sitter_id")
    private CareSitter careSitter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bedge_id")
    private Bedge bedge;
}
