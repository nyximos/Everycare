package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Board;
import wd.team4.everycare.domain.Contract;
import wd.team4.everycare.domain.Report;
import wd.team4.everycare.dto.report.ReportFormDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.BoardRepository;
import wd.team4.everycare.repository.ContractRepository;
import wd.team4.everycare.repository.ReportRepository;
import wd.team4.everycare.service.interfaces.ReportService;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ContractRepository contractRepository;
    private final BoardRepository boardRepository;

    @Override
    public ResponseEntity<MyResponse> saveCareReports(PrincipalDetails principalDetails, ReportFormDTO reportFormDTO) {

        Long contractId = reportFormDTO.getContractId();
        Optional<Contract> contract = contractRepository.findById(contractId);
        Contract contractEntity = contract.orElse(null);

        Report report = Report.builder()
                .createdAt(LocalDateTime.now())
                .type("care")
                .status(0)
                .reason(reportFormDTO.getReason())
                .reportedUserId(reportFormDTO.getReportedUserId())
                .contract(contractEntity)
                .member(principalDetails.getUser())
                .build();

        reportRepository.save(report);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> saveBoardReports(PrincipalDetails principalDetails, ReportFormDTO reportFormDTO) {

        Optional<Board> board = boardRepository.findById(reportFormDTO.getBoardId());
        Board boardEntity = board.orElse(null);

        Report report = Report.builder()
                .createdAt(LocalDateTime.now())
                .type("board")
                .status(0)
                .reason(reportFormDTO.getReason())
                .reportedUserId(reportFormDTO.getReportedUserId())
                .board(boardEntity)
                .member(principalDetails.getUser())
                .build();

        reportRepository.save(report);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

}
