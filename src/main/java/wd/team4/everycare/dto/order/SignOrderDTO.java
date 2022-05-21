package wd.team4.everycare.dto.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.domain.OrderStatus;
import wd.team4.everycare.dto.member.MemberListViewDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class SignOrderDTO {
    private Long id;
    private String name;
    private int amount;
    private String recipientName;
    private int recipientNumber;
    private String zipcode;
    private String address;
    private String detailedAddress;
    private OrderStatus status;
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime orderTime;
    private String comment;
    private int paymentAmount;
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime paymentTime;
    private String cardCompany;
    private String cardNumber;
    private int approvalNumber;
    private int installmentPlan;
    private MemberListViewDTO member;
}
