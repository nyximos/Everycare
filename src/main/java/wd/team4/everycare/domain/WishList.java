package wd.team4.everycare.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@Table(name = "wish_list")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "wish_list_seq_generator",
        sequenceName = "wish_list_seq",
        initialValue = 1, allocationSize = 1)

public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wishlist_seq_generator")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
