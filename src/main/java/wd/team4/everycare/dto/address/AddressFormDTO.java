package wd.team4.everycare.dto.address;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressFormDTO {

    private String recipientName;
    private int recipientNumber;
    private String zipcode;
    private String address;
    private String detailedAddress;
    private String comment;

}
