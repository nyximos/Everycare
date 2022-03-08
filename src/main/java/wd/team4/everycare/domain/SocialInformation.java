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
@SequenceGenerator( name ="social_information_generator",
        sequenceName = "social_information_seq",
        initialValue = 1, allocationSize = 1)
public class SocialInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "social_information_generator")
    @Column(name ="social_information_id", nullable = false)
    private String id;

    @Column(name = "social_information_type", length = 255, nullable = false)
    private String type;
}
