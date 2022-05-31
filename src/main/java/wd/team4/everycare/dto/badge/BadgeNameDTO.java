package wd.team4.everycare.dto.badge;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BadgeNameDTO {

    private Long id;
    private String fileName;
    private String filePath;
}
