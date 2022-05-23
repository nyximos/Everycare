package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.domain.Order;
import wd.team4.everycare.domain.OrderStatus;
import wd.team4.everycare.repository.MemberRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Component
@Transactional
@RequiredArgsConstructor
public class InitOrderService {

    private final EntityManager em;
    private final MemberRepository memberRepository;


    public void dbInit() {
        Member member = memberRepository.findById("member1").get();

        Order order1 = createOrder("name1", 1, "recipientName1", 1, "zipcode1", "address1", "detailedAddress1", OrderStatus.ORDER, LocalDateTime.now(), "comment1", member);
        Order order2 = createOrder("name2", 2, "recipientName2", 2, "zipcode2", "address2", "detailedAddress2", OrderStatus.ORDER, LocalDateTime.now(), "comment2", member);
        Order order3 = createOrder("name3", 3, "recipientName3", 3, "zipcode3", "address3", "detailedAddress3", OrderStatus.ORDER, LocalDateTime.now(), "comment3", member);
        Order order4 = createOrder("name4", 4, "recipientName4", 4, "zipcode4", "address4", "detailedAddress4", OrderStatus.ORDER, LocalDateTime.now(), "comment4", member);
        Order order5 = createOrder("name5", 5, "recipientName5", 5, "zipcode5", "address5", "detailedAddress5", OrderStatus.ORDER, LocalDateTime.now(), "comment5", member);

    }

    private Order createOrder(String name, int amount, String recipientName, int recipientNumber, String zipcode, String address, String detailedAddress, OrderStatus status, LocalDateTime orderTime, String comment, Member member) {
        Order order = Order.builder()
                .name(name)
                .amount(amount)
                .recipientName(recipientName)
                .recipientNumber(recipientNumber)
                .zipcode(zipcode)
                .address(address)
                .detailedAddress(detailedAddress)
                .status(status)
                .orderTime(orderTime)
                .comment(comment)
                .member(member)
                .build();

        em.persist(order);

        return order;
    }
}