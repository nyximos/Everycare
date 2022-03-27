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
@SequenceGenerator(name = "wishlist_seq_generator",
        sequenceName = "wishlist_seq",
        initialValue = 1, allocationSize = 1)

public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wishlist_seq_generator")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
