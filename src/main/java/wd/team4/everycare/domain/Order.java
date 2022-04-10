package wd.team4.everycare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
@SequenceGenerator(name = "order_seq_generator",
        sequenceName = "order_seq",
        initialValue = 1, allocationSize = 1)
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq_generator")
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_name", nullable = false)
    private String name;

    @Column(name = "order_amount", nullable = false)
    private int amount;

    @Column(name = "order_recipient_name", length = 30, nullable = false)
    private String recipientName;

    @Column(name = "order_recipient_number", nullable = false)
    private int recipientNumber;

    @Column(name = "order_zipcode", length = 45, nullable = false)
    private String zipcode;

    @Column(name = "order_address", nullable = false)
    private String address;

    @Column(name = "order_detailed_address", nullable = false)
    private String detailedAddress;

    @Column(name = "order_status", length = 30, nullable = false)
    private String status;

    @Column(name = "order_time", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime orderTime;

    @Column(name = "order_delivery_comment")
    private String comment;

    @Column(name = "order_payment_amount")
    private int paymentAmount;

    @Column(name = "order_payment_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime paymentTime;

    @Column(name = "order_credit_card_company")
    private String cardCompany;

    @Column(name = "order_card_number", length = 55)
    private String cardNumber;

    @Column(name = "order_payment_approval_number")
    private int approvalNumber;

    @Column(name = "order_monthly_installment_plan")
    private int installmentPlan;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
