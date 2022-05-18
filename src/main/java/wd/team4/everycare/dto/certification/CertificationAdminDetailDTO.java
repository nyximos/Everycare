package wd.team4.everycare.dto.certification;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificationAdminDetailDTO {

    private Long id;
    private String name;
    private String uploadName;
    private String storeName;
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;
    private String memberId;
    private String memberName;
}
