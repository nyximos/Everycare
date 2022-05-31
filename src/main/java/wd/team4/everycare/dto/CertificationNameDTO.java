package wd.team4.everycare.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CertificationNameDTO {

    private Long id;
    private String name;
    private String storeFileName;
    private String uploadFileName;
    private int adminApproval;

}
