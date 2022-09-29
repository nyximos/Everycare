package wd.team4.everycare.dto.careNote;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityInformationDTO {

    private Long id;
    private String name;
    private String startTime;
    private String endTime;
    private String requirement;
    private String content;
    private String storeFileName;
    private Long ACId;
}
