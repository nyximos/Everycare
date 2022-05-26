package wd.team4.everycare.dto.order;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {

    private Long id;
    private String name;
    private int amount;
    private String recipientName;
    private int recipientNumber;
    private String zipcode;
    private String address;
    private String detailedAddress;
    private String comment;
    private int paymentAmount;
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime paymentTime;

}
