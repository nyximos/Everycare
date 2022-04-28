package wd.team4.everycare.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CertificationViewDTO {

    private Long id;
    private String name;
    private String uploadFileName;
    private String storeFileName;
    private int adminApproval;
    private LocalDateTime createdAt;

}
