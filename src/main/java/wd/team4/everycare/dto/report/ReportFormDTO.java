package wd.team4.everycare.dto.report;

import lombok.*;
import wd.team4.everycare.domain.Board;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportFormDTO {
    private Long id;
    private String reason;
    private String reportedUserId;
    private Long boardId;
    private Long contractId;

}
