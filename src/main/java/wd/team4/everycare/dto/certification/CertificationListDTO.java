package wd.team4.everycare.dto.certification;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificationListDTO {

    private Long id;
    private String classificationName;
    private String memberId;
    private String memberName;
}
