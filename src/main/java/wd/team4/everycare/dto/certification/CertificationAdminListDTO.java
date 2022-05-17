package wd.team4.everycare.dto.certification;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificationAdminListDTO {

    private Long id;
    private String name;
    private String memberId;
    private String memberName;
}
