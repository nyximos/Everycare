package wd.team4.everycare.dto.address;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressViewDTO {

    private Long id;
    private String zipcode;
    private String address;
    private String detailedAddress;


}
