package wd.team4.everycare.domain;

import lombok.*;
import wd.team4.everycare.dto.badge.CareSitterBadgeDTO;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "care_sitter_bedge")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "care_sitter_bedge_seq_generator",
        sequenceName = "care_sitter_bedge_seq",
        initialValue = 1, allocationSize = 1)
@AllArgsConstructor
@Builder
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

    public CareSitterBadgeDTO toCareSitterBadgeDTO(){
        return CareSitterBadgeDTO.builder()
                .id(this.id)
                .careSitter(this.careSitter.toNameDTO())
                .badge(this.bedge.toBadgeNameDTO())
                .build();
    }
}
