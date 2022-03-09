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
@Table(name = "job_offer_care_sitter")
@SequenceGenerator(name = "job_offer_care_sitter_seq_generator",
        sequenceName = "job_offer_care_sitter_seq",
        initialValue = 1, allocationSize = 1)
public class JobOfferCareSitter {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_offer_care_sitter_seq_generator")
    @Column(name = "job_offer_care_sitter_id")
    private Long JobOfferCareSitter;

    @ManyToOne
    @JoinColumn(name = "job_offer_id")
    private JobOffer jobOffer;

    @ManyToOne
    @JoinColumn(name = "care_sitter_profile_id")
    private CareSitter careSitter;

}
