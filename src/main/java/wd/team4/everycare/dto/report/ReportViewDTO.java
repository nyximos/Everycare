package wd.team4.everycare.dto.report;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import wd.team4.everycare.domain.Board;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportViewDTO {

    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;
    private String type;
    private int status;
    private String reason;

    private String memberId;
    private String memberName;

    private String reportedUserId;

    private Long boardId;
    private String boardTitle;

    private Long contractId;
    private String contractName;

}
