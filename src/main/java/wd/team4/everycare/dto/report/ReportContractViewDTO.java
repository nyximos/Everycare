package wd.team4.everycare.dto.report;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportContractViewDTO {

    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;
    private String type;
    private int status;
    private String reason;

    private String memberId;
    private String memberName;

    private String reportedUserId;

    private Long contractId;
    private String contractName;

    }
