package wd.team4.everycare.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "social_information")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator( name ="social_information_generator",
        sequenceName = "social_information_seq",
        initialValue = 1, allocationSize = 1)
public class SocialInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "social_information_generator")
    @Column(name ="social_information_id", nullable = false)
    private String id;

    @Column(name = "social_information_type", nullable = false)
    private String type;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
