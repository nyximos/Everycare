package wd.team4.everycare.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PayResponse {
    private String paymentKey;
    private String orderId;
    private String method;
    private String status;
    private LocalDateTime requestAt;
    private LocalDateTime approvedAt;
    private boolean useEscrow;
    private String cardCompany;
    private String cardNumber;
    private int installmentPlanMonths;
    private boolean isInterestFree;
    private String approveNo;
    private boolean useCardPoint;
    private String cardType;
    private String ownerType;
    private String acquireStatus;
    private int totalAmount;
    private int balanceAmount;
}
