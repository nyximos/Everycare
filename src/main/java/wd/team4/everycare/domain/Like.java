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
@Table(name = "like")
@SequenceGenerator(name = "like_seq_generator",
        sequenceName = "like_seq",
        initialValue = 1, allocationSize = 1)

public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "like_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
