package wd.team4.everycare.dto.order;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {

    private String name;
    private int amount;
    private String recipientName;
    private int recipientNumber;
    private String zipcode;
    private String address;
    private String detailedAddress;
    private String comment;
    private int paymentAmount;
    private LocalDateTime paymentTime;

}
