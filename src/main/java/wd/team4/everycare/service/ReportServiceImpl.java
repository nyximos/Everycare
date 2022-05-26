package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.*;
import wd.team4.everycare.dto.careSitterReview.CareSitterReviewDTO;
import wd.team4.everycare.dto.report.ReportFormDTO;
import wd.team4.everycare.dto.report.ReportViewDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.BoardRepository;
import wd.team4.everycare.repository.ContractRepository;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.repository.ReportRepository;
import wd.team4.everycare.service.interfaces.ReportService;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ContractRepository contractRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

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

    @Override
    public ResponseEntity<MyResponse> getAll() {

        List<Report> reports = reportRepository.findAll();
        List<ReportViewDTO> reportDTOs = new ArrayList();

        for (Report report : reports) {
            ReportViewDTO dto = ReportViewDTO.builder()
                    .id(report.getId())
                    .createdAt(report.getCreatedAt())
                    .type(report.getType())
                    .status(report.getStatus())
                    .reason(report.getReason())
                    .memberId(report.getMember().getId())
                    .memberName(report.getMember().getName())
                    .reportedUserId(report.getReportedUserId())
                    .build();

            if (report.getBoard() != null) {
                dto.setBoardId(report.getBoard().getId());
                dto.setBoardTitle(report.getBoard().getTitle());
            }
            if(report.getContract() != null) {
                dto.setContractId(report.getContract().getId());
                dto.setContractName(report.getContract().getName());
            }

            reportDTOs.add(dto);
        }

        MyResponse<List<ReportViewDTO>> body = MyResponse.<List<ReportViewDTO>>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(reportDTOs)
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> getCompletion() {

        List<Report> reports = reportRepository.findByStatus(1);
        List<ReportViewDTO> reportDTOs = new ArrayList();

        for (Report report : reports) {
            ReportViewDTO dto = ReportViewDTO.builder()
                    .id(report.getId())
                    .createdAt(report.getCreatedAt())
                    .type(report.getType())
                    .status(report.getStatus())
                    .reason(report.getReason())
                    .memberId(report.getMember().getId())
                    .memberName(report.getMember().getName())
                    .reportedUserId(report.getReportedUserId())
                    .build();

            if (report.getBoard() != null) {
                dto.setBoardId(report.getBoard().getId());
                dto.setBoardTitle(report.getBoard().getTitle());
            }
            if(report.getContract() != null) {
                dto.setContractId(report.getContract().getId());
                dto.setContractName(report.getContract().getName());
            }

            reportDTOs.add(dto);
        }

        MyResponse<List<ReportViewDTO>> body = MyResponse.<List<ReportViewDTO>>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(reportDTOs)
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);    }

    @Override
    public ResponseEntity<MyResponse> getHold() {

        List<Report> reports = reportRepository.findByStatus(0);
        List<ReportViewDTO> reportDTOs = new ArrayList();

        for (Report report : reports) {
            ReportViewDTO dto = ReportViewDTO.builder()
                    .id(report.getId())
                    .createdAt(report.getCreatedAt())
                    .type(report.getType())
                    .status(report.getStatus())
                    .reason(report.getReason())
                    .memberId(report.getMember().getId())
                    .memberName(report.getMember().getName())
                    .reportedUserId(report.getReportedUserId())
                    .build();

            if (report.getBoard() != null) {
                dto.setBoardId(report.getBoard().getId());
                dto.setBoardTitle(report.getBoard().getTitle());
            }
            if(report.getContract() != null) {
                dto.setContractId(report.getContract().getId());
                dto.setContractName(report.getContract().getName());
            }

            reportDTOs.add(dto);
        }

        MyResponse<List<ReportViewDTO>> body = MyResponse.<List<ReportViewDTO>>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(reportDTOs)
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);    }

    @Override
    public ResponseEntity<MyResponse> stop(String id) {

        Optional<Member> member = memberRepository.findById(id);
        Member memberEntity = member.orElse(null);
        memberEntity.updateStatus(ActivityStatus.STOP);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);    }

    @Override
    public ResponseEntity<MyResponse> active(String id) {

        Optional<Member> member = memberRepository.findById(id);
        Member memberEntity = member.orElse(null);
        memberEntity.updateStatus(ActivityStatus.ACTIVE);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);    }

}
